class NPC {
    protected String name = "";

    NPC(String name) {
        this.name = name;
    }

    String getName() {
        System.out.println("****************");
        return this.name;
    }
}
