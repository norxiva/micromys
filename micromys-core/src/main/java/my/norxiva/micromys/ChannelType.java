package my.norxiva.micromys;

public enum ChannelType {
    FUYOU("富友"),
    NETEASE("网易");

    private String name;

    ChannelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
