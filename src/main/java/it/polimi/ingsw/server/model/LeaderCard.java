package it.polimi.ingsw.server.model;

public class LeaderCard {
    private String id;
    private LeaderRequirements requirements;
    private Integer victoryPoint;
    private SpecialAbility specialAbility;
    private Boolean played;

    public LeaderCard (String id, LeaderRequirements requirements, Integer victoryPoint, SpecialAbility specialAbility, Boolean played){
        this.id = id;
        this.requirements = requirements;
        this.victoryPoint = victoryPoint;
        this.specialAbility = specialAbility;
        this.played = played;
    }

    public String getId(){return id;}
    public LeaderRequirements getRequirements(){return requirements;}
    public Integer getVictoryPoint(){return victoryPoint;}
    public SpecialAbility getSpecialAbility(){return specialAbility;}
    public Boolean isPlayed(){return played;}
    /** to do
     *
     */
    public void playCard(){/**to do*/;}
}
