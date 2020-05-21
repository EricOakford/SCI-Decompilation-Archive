;;; Sierra Script 1.0 - (do not remove this comment)
(script# 670)
(include game.sh) (include "670.shm")
(use Main)
(use FPRoom)
(use Scaler)
(use RandCyc)
(use Polygon)
(use Feature)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	rm670 0
)

(local
	talkedToMadame
	evtX
)
(instance rm670 of FPRoom
	(properties
		noun 25
		picture 670
		style FADEOUT
		horizon 40
		south 230
	)
	
	(method (init)
		(self setRegions: rgFreddy)
		(ego
			x: 47
			init:
			setScale: Scaler 120 63 189 97
			normalize:
		)
		(super init:)
		(theMusic
			number: (if (> numVoices 11) 670 else 1670)
			loop: -1
			play:
		)
		(madame
			init:
			setCycle: StopWalk -1
			setHeading: 270
			setScale: -1 ego
			setScript: sLookAtEgo
			approachVerbs: 9 4 2
		)
		(barkeep
			init:
			setCycle: RandCycle
			setPri: 9
			approachVerbs: V_TALK V_DO V_BOTTLE_FULL
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						71 189
						127 159
						217 174
						298 174
						307 132
						165 135
						155 122
						93 124
						76 129
						76 136
						60 138
						16 157
						16 189
					yourself:
				)
		)
		(doc init: setPri: 9 approachVerbs: V_BOTTLE_FULL V_BEER V_DO V_TALK)
		(if (not (Btst fGaveDocCoffee))
			(doc setScript: sDrink)
		)
		(if (not (ego has: iMatches))
			(matches init: setPri: 10 approachVerbs: V_DO)
		)
		(littlePic
			init:
			setOnMeCheck: dynamicName cYELLOW
		)
		(backDoor init:)
		(bar
			init:
			setOnMeCheck: dynamicName cLMAGENTA
		)
		(bottles init:)
		(fortuneTable
			init:
			setOnMeCheck: dynamicName cLBLUE
		)
		(fortuneWheel init:)
		(hatRack
			init:
			setOnMeCheck: dynamicName cMAGENTA
		)
		(moose
			init:
			setOnMeCheck: dynamicName cRED
		)
		(painting1 init:)
		(painting2
			init:
			setOnMeCheck: dynamicName cLRED
		)
		(piano
			init:
			setOnMeCheck: dynamicName cLGREEN
		)
		(chandelier
			init:
			setOnMeCheck: dynamicName cLCYAN
		)
		(diceGame
			init:
			setOnMeCheck: dynamicName cCYAN
		)
		(poolTable
			init:
			setOnMeCheck: dynamicName cBLUE
		)
		(roulette
			init:
			setOnMeCheck: dynamicName cGREEN
		)
		(stage init:)
		(table1
			init:
			setOnMeCheck: dynamicName cGREY
		)
		(table2
			init:
			setOnMeCheck: dynamicName cLGREY
		)
		(bullRack
			init:
			setOnMeCheck: dynamicName cBROWN
		)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 10 1)
		(super dispose:)
	)
)

(instance sDoMadame of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID tlkMadame MADAME) disposeWhenDone: FALSE)
				(messager say: N_MADAME V_DO NULL 0 self)
			)
			(1
				((ScriptID tlkMadame MADAME) disposeWhenDone: TRUE)
				(= next sLookAtEgo)
				(self dispose:)
			)
		)
	)
)

(instance sTalkMadame of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_MADAME V_TALK C_FIRST_TIME 1 3 self)
			)
			(1
				((ScriptID tlkMadame MADAME) disposeWhenDone: 0)
				(messager say: N_MADAME V_TALK C_FIRST_TIME 4 5 self)
			)
			(2
				((ScriptID tlkMadame MADAME) disposeWhenDone: 1)
				(messager say: N_MADAME V_TALK C_FIRST_TIME 6 8 self)
			)
			(3
				(= next sLookAtEgo)
				(self dispose:)
			)
		)
	)
)

(instance sMoleMadame of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_MADAME V_MOLE NULL 1 6 self)
			)
			(1
				((ScriptID tlkMadame MADAME) disposeWhenDone: FALSE)
				(messager say: N_MADAME V_MOLE NULL 7 8 self)
			)
			(2
				((ScriptID tlkMadame MADAME) disposeWhenDone: TRUE)
				(messager say: N_MADAME V_MOLE NULL 9 self)
			)
			(3
				(= next sLookAtEgo)
				(self dispose:)
			)
		)
	)
)

(instance sLookAtEgo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 30)
			)
			(1
				(Face madame ego self)
			)
			(2
				(self changeState: 0)
			)
		)
	)
)

(instance sDrink of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(client setCycle: EndLoop self)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance sGetSulfur of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: iEmptyVial)
				(doc view: 673 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(doc setCycle: BegLoop self)
			)
			(2
				(ego get: -1 iFullVial)
				(self dispose:)
			)
		)
	)
)

(instance srini of Actor
	(properties
		x 50
		y 320
		view 801
	)
	
	(method (init)
		(super init:)
		(self setCycle: StopWalk -1 setScale: -1 ego)
	)
)

(instance madame of Actor
	(properties
		x 283
		y 129
		noun N_MADAME
		approachX 256
		approachY 134
		view 802
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(17
				(if (Btst fGaveMoleToMadame)
					(messager say: noun theVerb C_MADAME_HAS_MOLE)
				else
					(messager say: noun theVerb C_MADAME_NEEDS_MOLE)
				)
			)
			(V_MOLE
				(ego put: iMole)
				(Bset fGaveMoleToMadame)
				(self setScript: sMoleMadame)
			)
			(V_SALTPETER_EMPTY
				(if (Btst fGaveMoleToMadame)
					(messager say: noun theVerb C_MADAME_HAS_MOLE)
					(ego put: iSaltpeter)
					(ego get: -1 iFullSaltpeter)
					(Bset fGotSaltpeter)
				else
					(messager say: noun theVerb C_MADAME_NEEDS_MOLE)
				)
			)
			(V_TALK
				(cond 
					((and (Btst fGaveMoleToMadame) (Btst fGotSaltpeter))
						(messager say: noun theVerb C_GOT_SALTPETER)
					)
					((Btst fGaveMoleToMadame)
						(messager say: noun theVerb C_MADAME_HAS_MOLE)
					)
					(talkedToMadame
						(messager say: noun theVerb C_GOT_BEER)
					)
					(else
						(self setScript: sTalkMadame)
						(= talkedToMadame TRUE)
					)
					
				)
			)
			(V_DO
				(self setScript: sDoMadame)
			)
			(else 
				(cond 
					(
						(and
							(& ((ScriptID 0 12) doit: theVerb) FORCE)
							(Btst fGaveMoleToMadame)
						)
						(messager say: noun V_USEIT C_MADAME_HAS_MOLE)
					)
					((& ((ScriptID 0 12) doit: theVerb) FORCE)
						(messager say: noun V_USEIT C_MADAME_NEEDS_MOLE)
					)
					(else
						(super doVerb: theVerb &rest)
					)
				)
			)
		)
	)
)

(instance barkeep of Actor
	(properties
		x 17
		y 110
		noun N_BARKEEP
		approachX 32
		approachY 151
		view 671
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(cond 
					((ego has: iFullCup)
						(messager say: noun theVerb C_GOT_COFFEE)
					)
					((or (Btst fDrankBeer) (ego has: iBeerBottle))
						(messager say: noun theVerb C_GOT_BEER)
						(ego get: -1 iFullCup)
						(if (ego has: iEmptyCup)
							(ego put: iEmptyCup)
						)
					)
					(else
						(messager say: noun theVerb C_FIRST_TIME)
						(ego get: -1 iBeerBottle)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doc of Prop
	(properties
		x 164
		y 103
		noun 7
		approachX 154
		approachY 127
		view 672
		signal skipCheck
	)
	
	(method (handleEvent event)
		(= evtX (event x?))
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb &tmp theCase)
		(if (> evtX 190)
			(if (OneOf theVerb V_LOOK V_DO)
				(messager say: noun theVerb 15)
			else
				(VerbFail)
			)
			(= evtX 0)
		else
			(switch theVerb
				(V_BOTTLE_FULL
					(Bset fGaveDocCoffee)
					(ego put: iFullBottle)
					(messager say: noun theVerb)
				)
				(V_BEER
					(ego put: iBeerBottle)
					(messager say: noun theVerb)
				)
				(V_SALTPETER_EMPTY
					(= theCase (if (Btst fGaveDocCoffee) 12 else 13))
					(messager say: noun theVerb theCase)
				)
				(V_TALK
					(cond 
						((and (Btst fGaveDocCoffee) (ego has: iEmptyVial))
							(= theCase 11)
						)
						((ego has: 1)
							(= theCase 14)
						)
						((Btst fGaveDocCoffee)
							(= theCase 10)
						)
						((ego has: iEmptyVial)
							(= theCase 9)
						)
						(else
							(= theCase 8)
						)
					)
					(messager say: noun theVerb theCase)
				)
				(V_VIAL_EMPTY
					(= theCase (if (Btst fGaveDocCoffee) 11 else 9))
					(if (Btst fGaveDocCoffee) (curRoom setScript: sGetSulfur))
					(messager say: noun theVerb theCase)
				)
				(else 
					(if (OneOf theVerb
						V_BOTTLE_EMPTY V_CHARCOAL V_CUP_EMPTY V_CUP_FULL V_SALTPETER_FULL
						V_FUSE V_MATCHES V_MOLE V_PRESCRIPTION V_LOOK V_DO V_TINCAN V_BOMB_UNLIT
						)
						(if (Btst fGaveDocCoffee)
							(messager say: noun theVerb 12)
						else
							(messager say: noun theVerb 13)
						)
					else
						(super doVerb: theVerb &rest)
					)
				)
			)
		)
	)
)

(instance backDoor of View
	(properties
		x 213
		y 89
		noun N_BACKDOOR
		approachX 222
		approachY 81
		approachDist 14
		view 670
		loop 1
	)
)

(instance matches of View
	(properties
		x 54
		y 102
		noun N_MATCHES
		approachX 76
		approachY 132
		view 670
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego get: iMatches self)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bullRack of Feature
	(properties
		x 260
		y 38
		noun N_BULLRACK
	)
)

(instance bottles of Feature
	(properties
		x 12
		y 86
		noun N_BOTTLES
		nsTop 72
		nsBottom 100
		nsRight 24
	)
)

(instance poolTable of Feature
	(properties
		x 155
		y 175
		noun N_POOLTABLE
	)
)

(instance roulette of Feature
	(properties
		x 285
		y 179
		noun N_ROULETTE
	)
)

(instance diceGame of Feature
	(properties
		x 310
		y 150
		noun N_DICEGAME
	)
)

(instance moose of Feature
	(properties
		x 289
		y 200
		noun N_MOOSE
	)
)

(instance hatRack of Feature
	(properties
		x 245
		y 72
		noun N_HATRACK
	)
)

(instance painting1 of Feature
	(properties
		x 223
		y 31
		noun N_PAINTING1
		nsTop 19
		nsLeft 210
		nsBottom 47
		nsRight 239
	)
)

(instance table1 of Feature
	(properties
		x 107
		y 89
		noun N_TABLE1
	)
)

(instance table2 of Feature
	(properties
		x 198
		y 98
		noun N_TABLE2
	)
)

(instance fortuneTable of Feature
	(properties
		x 281
		y 103
		noun N_FORTUNETABLE
	)
)

(instance piano of Feature
	(properties
		x 47
		y 81
		noun N_PIANO
	)
)

(instance chandelier of Feature
	(properties
		x 105
		y 100
		noun N_CHANDELIER
	)
)

(instance painting2 of Feature
	(properties
		x 7
		y 39
		noun N_PAINTING2
	)
)

(instance littlePic of Feature
	(properties
		x 32
		y 38
		noun N_LITTLEPIC
	)
)

(instance bar of Feature
	(properties
		x 26
		y 120
		noun N_BAR
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (ego has: iMatches)
					(messager say: noun theVerb 17)
				else
					(messager say: noun theVerb 16)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fortuneWheel of Feature
	(properties
		x 309
		y 90
		noun N_FORTUNEWHEEL
		nsTop 61
		nsLeft 299
		nsBottom 119
		nsRight 319
		sightAngle 40
		approachX 242
		approachY 125
		approachDist 107
	)
)

(instance stage of Feature
	(properties
		x 127
		y 47
		noun N_STAGE
		nsTop 17
		nsLeft 60
		nsBottom 78
		nsRight 194
		sightAngle 40
		approachX 143
		approachY 127
		approachDist 92
	)
)
