package it.polimi.ingsw.server.model;

public class LeaderCard {
    private Integer id;
    private LeaderRequirements requirements;
    private Integer victoryPoint;
    private SpecialAbility specialAbility;
    private Boolean played;

    public LeaderCard (Integer id, LeaderRequirements requirements, Integer victoryPoint, SpecialAbility specialAbility){
        this.id = id;
        this.requirements = requirements;
        this.victoryPoint = victoryPoint;
        this.specialAbility = specialAbility;
        this.played = false;
    }

    public Integer getId(){return id;}
    public LeaderRequirements getRequirements(){return requirements;}
    public Integer getVictoryPoint(){return victoryPoint;}
    public SpecialAbility getSpecialAbility(){return specialAbility;}
    public Boolean isPlayed(){return played;}
    /** to do
     *
     */
    public void playCard(RealPlayer realPlayer){
        if(this.requirements.check(realPlayer)){
            this.played= true;
        }
    }
}
