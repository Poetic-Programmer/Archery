package games.mgd.archery.logic;

/**
 * <h1> World Singleton </h1>
 * <p> holds data relevant to the setup and movement of game objects. all objects should
 * be positioned in the world using this class to use appropriate range.
 *
 * Class should be initialized whenever the camera is set up </p>
 *
 * @author Michael Dunleavy
 * @since 24/07/2016.
 */

public enum World {
    INSTANCE;

    private float worldWidth;
    private float worldHeight;

    private float halfWorldWidth;
    private float halfWorldHeight;

    private float gridCellWidth;
    private float gridCellHeight;

    private float gravity;


    World()
    {
        worldWidth = 30;
        worldHeight = 20;

        gridCellWidth = worldWidth / 16.0f;
        gridCellHeight = worldHeight / 16.0f;

        halfWorldWidth = worldWidth * 0.5f;
        halfWorldHeight = worldHeight * 0.5f;
        
        gravity = -9.8f;
    }

    public float getWidth() { return worldWidth; }
    public float getHeight() { return worldHeight; }
    public float getHalfWidth() { return halfWorldWidth; }
    public float getHalfHeight() { return halfWorldHeight; }
    public float getGridCellWidth() { return gridCellWidth; }
    public float getGridCellHeight() { return gridCellHeight; }

    /**
     * this ensures objects positioned using this as a reference will be placed at the same point
     * regardless of device size.
     *
     * @param val size relative to a percentage of cell width [0, 100%] -> [0, 1]
     * @return a uniform value based on the size of the grids cell
     */
    public float getCellRelWidth(final float val) { return gridCellWidth * val; }
    /**
     * this ensures objects positioned using this as a reference will be placed at the same point
     * regardless of device size.
     *
     * @param val size relative to a percentage of cell height [0, 100%] -> [0, 1]
     * @return a uniform value based on the size of the grids cell
     */
    public float getCellRelHeight(final float val) { return gridCellHeight * val; }
    public float getCellX(final int rowIndex) { return gridCellWidth * rowIndex; }
    public float getCellY(final int colIndex) { return gridCellHeight * colIndex; }
    public float getGravity() { return gravity; }
}
