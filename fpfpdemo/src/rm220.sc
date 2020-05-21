;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include game.sh) (include "220.shm")
(use Main)
(use FPRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use MoveFwd)
(use Door)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm220 0
)

(local
	nearSmithie
)
(instance rm220 of FPRoom
	(properties
		picture 220
		horizon 40
		east 230
	)
	
	(method (init)
		(self setRegions: rgFreddy)
		(switch prevRoomNum
			(east
				(= style SCROLLRIGHT)
			)
			(else
				(= style FADEOUT)
			)
		)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(super init:)
		(if (and (not (ego has: iMole)) (not (Btst fGaveMoleToMadame)))
			(theMusic
				number: (if (> numVoices 11) 601 else 1601)
				loop: -1
				play:
			)
			(smithie
				init:
				approachVerbs: V_DO V_TALK V_LOOK
				setScale: -1 ego
				setScript: sMakeHorseshoe
			)
			(anvilSound play:)
			(coals init: setPri: 7 setCycle: Forward)
			(horse addToPic:)
		else
			(if (!= (theMusic number?) 200)
				(theMusic number: 200 loop: -1 play:)
			)
			(smithDoor addToPic:)
		)
		(bankDoor init: approachVerbs: V_DO)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 119
						312 119
						305 94
						295 94
						290 67
						303 67
						286 3
						267 3
						277 29
						272 48
						232 48
						231 95
						193 110
						78 111
						67 98
						41 106
						0 109
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						82 114
						188 114
						188 125
						82 125
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 167
						207 167
						211 189
						0 189
					yourself:
				)
		)
		(blacksmith
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cGREEN
		)
		(sidewalk
			init:
			setOnMeCheck: dynamicName cLMAGENTA
		)
		(anvil
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cLGREEN
		)
		(wagon
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cLRED
		)
		(fence
			init:
			setOnMeCheck: dynamicName cLGREY
		)
		(saddle
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cRED
		)
		(corral
			init:
			setOnMeCheck: dynamicName cCYAN
		)
		(balanceStreet
			init:
			setOnMeCheck: dynamicName cLBLUE
		)
		(bank
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cBLUE
		)
		(mainStreet
			init:
			setOnMeCheck: dynamicName cGREY
		)
		(trough
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cMAGENTA
		)
		(rail
			init:
			setOnMeCheck: dynamicName cBROWN
		)
		(firepit
			init:
			approachVerbs: V_DO
			setOnMeCheck: dynamicName cLCYAN
		)
		(theGame handsOn:)
	)
	
	(method (doit)
		(if script
		else
			(switch ((User alterEgo?) edgeHit?)
				(NORTH
					(curRoom setScript: sComeBackLittleFreddy)
				)
				(SOUTH
					(ego setHeading: 180)
					(curRoom setScript: sComeBackLittleFreddy)
				)
				(WEST
					(ego setHeading: 270)
					(curRoom setScript: sComeBackLittleFreddy)
				)
			)
		)
		(super doit:)
	)
)

(instance sMakeHorseshoe of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (client cel?) 0)
				(== (client loop?) 0)
				(== (anvilSound prevSignal?) -1)
			)
			(anvilSound number: 226 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: 0 setCycle: Forward)
				(= seconds (Random 2 15))
			)
			(1
				(client setCycle: EndLoop self)
			)
			(2
				(client setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(= ticks 90)
			)
			(4
				(client setCycle: BegLoop self)
			)
			(5
				(self changeState: 0)
			)
		)
	)
)

(instance sComeBackLittleFreddy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 5)
			)
			(1
				(if (OneOf (ego heading?) 270 180)
					(ego setMotion: MoveFwd 80 self)
				else
					(= cycles 1)
				)
			)
			(2
				(= seconds 4)
			)
			(3
				(switch (ego heading?)
					(180
						(ego setHeading: 0)
					)
					(270
						(ego setHeading: 90)
					)
					(else
						(ego setHeading: 180)
					)
				)
				(= cycles 5)
			)
			(4
				(if (OneOf (ego heading?) 0 90)
					(ego setMotion: MoveFwd 90 self)
				else
					(ego setMotion: MoveFwd 30 self)
				)
			)
			(5
				(messager say: N_CANT_GO NULL NULL 0 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance coals of Prop
	(properties
		y 94
		view 220
		loop 2
	)
)

(instance smithie of Prop
	(properties
		x 43
		y 97
		noun N_SMITHIE
		approachX 15
		approachY 108
		view 221
		cycleSpeed 15
	)
	
	(method (handleEvent event)
		(if (< (event y?) 85)
			(= nearSmithie TRUE)
		else
			(= nearSmithie FALSE)
		)
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb &tmp theCase)
		(switch theVerb
			(V_DO
				(if (and nearSmithie (not (Btst fGotMole)))
					(= theCase C_ONFACE)
					(ego get: -1 iMole)
					(Bset fGotMole)
				else
					(= theCase C_NOTONFACE)
				)
				(messager say: N_SMITHIE V_DO theCase)
			)
			(V_TALK
				(if (ego has: iMole)
					(= theCase C_AFTERMOLE)
				else
					(= theCase C_WITHMOLE)
				)
				(messager say: N_SMITHIE V_TALK theCase)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance horse of View
	(properties
		x 50
		y 52
		noun N_HORSE_ON_BLOX
		view 220
		loop 5
	)
)

(instance smithDoor of View
	(properties
		x 30
		y 86
		noun N_SMITHY
		view 220
		loop 1
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_AFTER_GONE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wagon of Feature
	(properties
		x 256
		y 45
		noun N_WAGON
		sightAngle 40
	)
)

(instance sidewalk of Feature
	(properties
		x 160
		y 45
		noun N_SIDEWALK
		sightAngle 40
	)
)

(instance firepit of Feature
	(properties
		x 7
		y 95
		noun N_FIREPIT
		sightAngle 40
	)
	
	(method (doVerb theVerb &tmp theCase)
		(= theCase (if (cast contains: smithie) C_FIREGOING else C_FIREOUT))
		(switch theVerb
			(V_LOOK
				(messager say: N_FIREPIT V_LOOK theCase)
			)
			(V_DO
				(if (or (ego has: iCharcoal) (Btst fPutCharcoalInCan))
					(messager say: N_FIREPIT V_DO C_NO_MORE_CHAR)
				else
					(messager say: N_FIREPIT V_DO theCase)
					(if (== theCase C_FIREOUT)
						(ego get: -1 iCharcoal)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance anvil of Feature
	(properties
		x 7
		y 95
		noun N_ANVIL
		sightAngle 40
	)
)

(instance blacksmith of Feature
	(properties
		x 50
		y 46
		noun N_SMITHY
		sightAngle 40
		approachX 53
		approachY 102
		approachDist 63
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (cast contains: smithie)
					(messager say: noun theVerb)
				else
					(messager say: noun theVerb C_AFTER_GONE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fence of Feature
	(properties
		x 97
		y 161
		noun N_FENCE
		sightAngle 40
		approachX 72
		approachY 151
		approachDist 34
	)
)

(instance saddle of Feature
	(properties
		x 97
		y 161
		noun N_SADDLE
		sightAngle 40
		approachX 73
		approachY 114
		approachDist 34
	)
)

(instance corral of Feature
	(properties
		x 97
		y 161
		noun N_CORRAL
		sightAngle 40
		approachX 72
		approachY 151
		approachDist 34
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BOMB_LIT
				(messager say: noun theVerb)
			)
			(else 
				(if (& ((ScriptID 0 12) doit: theVerb) FORCE)
					(messager say: noun V_USEIT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance balanceStreet of Feature
	(properties
		x 269
		y 74
		noun N_BALANCE
		sightAngle 40
		approachX 273
		approachY 126
		approachDist 34
	)
)

(instance bank of Feature
	(properties
		x 153
		y 52
		noun N_BANK
		sightAngle 40
		approachX 177
		approachY 112
		approachDist 69
	)
)

(instance mainStreet of Feature
	(properties
		x 97
		y 61
		noun N_STREET
		sightAngle 40
		approachX 72
		approachY 151
		approachDist 58
	)
)

(instance trough of Feature
	(properties
		x 100
		y 125
		noun N_TROUGH
		sightAngle 40
		approachX 129
		approachY 135
		approachDist 36
	)
)

(instance rail of Feature
	(properties
		x 143
		y 117
		noun N_RAIL
		sightAngle 40
		approachX 167
		approachY 120
		approachDist 32
	)
)

(instance bankDoor of Door
	(properties
		x 192
		y 91
		noun N_BANKDOOR
		approachX 205
		approachY 93
		view 220
		cel 2
		locked 1
	)
)

(instance anvilSound of Sound
	(properties
		flags mNOPAUSE
		number 225
	)
)
