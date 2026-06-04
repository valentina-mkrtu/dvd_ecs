# DVD Screensaver (Ashley ECS Edition)

A learning project built with **LibGDX** to practice the **Entity Component System (ECS)** architecture utilizing the **Ashley** framework

The project simulates the classic bouncing DVD logo screen, incorporating a "health" system that penalizes the logo when it impacts screen boundaries, and dynamically updates a UI health bar.

---

## Architectural Overview (ECS Breakdown)

To keep data separated from logic, this project splits the implementation into pure data buckets (Components) and iterative processors (Systems).

### Components (Data)
* **`PositionComponent` / `VelocityComponent`**: Tracks the 2D coordinates and speed vectors of moving elements.
* **`LogoComponent`**: Holds the texture reference and physical dimensions for rendering the DVD logo.
* **`HealthComponent` / `HealthBarComponent`**: Monitors the structural health state and maps out the dimensional boundaries for the UI health rendering.

### Systems (Logic & Rendering Order)
Ashley executes systems sequentially based on registration order. The layout ensures physics and rules process before visual output:

1. **`BounceSystem`**: Evaluates boundary collisions against `WORLD_WIDTH` and `WORLD_HEIGHT`, handling the directional deflection logic.
2. **`DamageSystem`**: Checks collision states and decrements health attributes upon boundary contact.
3. **`DeathSystem`**: Resolves zero-health states, acting as a reset mechanism that repositions the entity and penalizes its velocity.
4. **`RenderSystem`**: Draws the foundational game world layers (the DVD logo texture).
5. **`HealthBarRenderSystem`**: Renders user interface elements on top of the world layer, preventing the game world textures from overlapping UI components.

---

## Performance & Batching Optimizations

Unlike basic implementations that flag draw states per-entity, the render systems override the core engine update cycle:

* **Batch Flushing Minimization**: `SpriteBatch.begin()` and `end()` are triggered exactly once per system cycle rather than per-entity. This packages all drawing operations into single draw calls, drastically lowering CPU-to-GPU overhead.
* **Memory Management**: UI assets (like health bar background/foreground panels) utilize low-overhead single-pixel `Pixmap` textures generated and cached natively at startup to eliminate real-time I/O lag.

---

## Getting Started & Execution

This project uses Gradle to manage dependencies. The Gradle wrapper is included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.

### Core Commands

* **Run the Desktop Application:**
```bash
  ./gradlew lwjgl3:run
* **Build an Executable JAR:**
 (Outputs to lwjgl3/build/libs)
```bash
  ./gradlew lwjgl3:jar
* **Clean Build Directories:**
```bash
  ./gradlew clean
