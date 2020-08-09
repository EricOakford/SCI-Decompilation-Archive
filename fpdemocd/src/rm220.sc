;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include game.sh) (include "220.shm") (include "0.shm")
(use Main)
(use OccasionalCycle)
(use FPRoom)
(use Scaler)
(use Polygon)
(use Feature)
(use MoveFwd)
(use ForCount)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm220 0
)

(local
	[local0 4] = [0 0 20 1]
	smithyCued =  1
)
(instance rm220 of FPRoom
	(properties
		noun N_ROOM
		picture 220
		horizon 67
		east 230
		west 210
	)
	
	(method (init)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(switch prevRoomNum
			(west
				(if (< (ego y?) 109) (ego y: 109))
				(= style SCROLLLEFT)
				(theGame handsOn:)
			)
			(east
				(= style SCROLLRIGHT)
				(theGame handsOn:)
			)
			(else 
				(= style PLAIN)
				(ego x: 160 y: 160)
				(theGame handsOn:)
			)
		)
		(theMusic1 number: 221 flags: mNOPAUSE loop: -1 play:)
		(super init:)
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
						289 94
						283 67
						252 67
						248 92
						221 92
						209 100
						198 100
						198 84
						160 84
						161 88
						194 88
						194 111
						78 111
						82 92
						7 89
						7 95
						53 96
						50 106
						0 109
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 141
						10 142
						37 167
						222 168
						222 189
						0 189
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
						319 167
						319 189
						293 189
						293 167
					yourself:
				)
		)
		(horse
			init:
			approachVerbs: V_TALK
			setCycle: OccasionalCycle 1 45 200 2 8
		)
		(smithy
			init:
			approachVerbs: V_DO V_TALK V_LOOK
			setScale: -1 ego
			setScript: sMakeHorseshoe
		)
		(coals init: approachVerbs: V_DO setCycle: Forward setPri: 7)
		(bulletHole init:)
		(smithyDoor init:)
		(bankDoor init: approachVerbs: V_DO)
		(anvilFtr init: approachVerbs: V_DO setOnMeCheck: ftrControl cLGREEN)
		(firepit init: approachVerbs: V_DO setOnMeCheck: ftrControl cLCYAN)
		(blacksmith init: approachVerbs: V_DO setOnMeCheck: ftrControl cGREEN)
		(fence init: approachVerbs: V_DO setOnMeCheck: ftrControl cLGREY)
		(saddle init: approachVerbs: V_DO setOnMeCheck: ftrControl cRED)
		(corral init: approachVerbs: V_DO setOnMeCheck: ftrControl cCYAN)
		(bank init: approachVerbs: V_DO setOnMeCheck: ftrControl cBLUE)
		(trough init: setOnMeCheck: ftrControl cMAGENTA)
		(rail init: approachVerbs: V_DO setOnMeCheck: ftrControl cBROWN)
		(wagon init: approachVerbs: V_DO V_WALK setOnMeCheck: ftrControl cLRED)
		(sidewalk init: setOnMeCheck: ftrControl cLMAGENTA)
		(hay init: setOnMeCheck: ftrControl cYELLOW)
		(balanceStreet init: setOnMeCheck: ftrControl cLBLUE)
		(mainStreet init: setOnMeCheck: ftrControl cGREY)
		(rugMan init: hide: setPri: 0 setScript: sRandomShaker)
	)
	
	(method (doit)
		(if script
		else
			(switch ((User alterEgo?) edgeHit?)
				(NORTH
					(ego setHeading: 0)
					(curRoom setScript: sNoNorth)
				)
				(SOUTH
					(ego setHeading: 180)
					(curRoom setScript: sNoSouth)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(theMusic1 fade:)
		(super dispose:)
	)
)

(instance sNoSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveFwd 80 self)
			)
			(1
				(ego setHeading: 0)
				(= cycles 5)
			)
			(2
				(messager say: N_CANT_GO NULL NULL 0 self 0)
			)
			(3
				(ego setMotion: MoveFwd 90 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sNoNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 180)
				(= cycles 5)
			)
			(1
				(messager say: N_CANT_GO NULL NULL 0 self 0)
			)
			(2
				(ego setMotion: MoveFwd 10 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sMakeHorseshoe of Script
	
	(method (doit)
		(super doit:)
		(cond 
			((and (not smithyCued) (!= (smithy cel?) 0))
				(= smithyCued TRUE)
			)
			((and smithyCued (== (smithy cel?) 0))
				(smithieSound number: 2225 play:)
				(= smithyCued FALSE)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: 0 setCycle: Forward)
				(= seconds (Random 2 15))
			)
			(1 (client setCycle: EndLoop self))
			(2
				(client setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3 (= ticks 90))
			(4 (client setCycle: BegLoop self))
			(5 (self changeState: 0))
		)
	)
)

(instance sRandomShaker of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 100)))
			(1
				(shakeSound play:)
				(client
					show:
					setLoop: 1
					cel: 0
					setCycle: ForwardCounter 5 self
				)
			)
			(2
				(shakeSound stop:)
				(client
					setLoop: 0
					cel: (client lastCel:)
					setCycle: BegLoop self
				)
			)
			(3
				(client cel: 0 hide:)
				(self changeState: 0)
			)
		)
	)
)

(instance rugMan of Prop
	(properties
		x 225
		y 53
		z 30
		noun N_RUGMAN
		view 222
		detailLevel 2
	)
)

(instance smithy of Prop
	(properties
		x 43
		y 97
		noun N_SMITHY
		approachX 52
		approachY 102
		view 221
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(= smithyCued TRUE)
	)
)

(instance coals of Prop
	(properties
		y 95
		noun N_COALS
		approachX 1
		approachY 93
		view 220
		loop 2
		signal ignrAct
		detailLevel 2
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_FIREGOING)
			)
			(V_DO
				(messager say: noun theVerb C_FIREGOING)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance horse of Prop
	(properties
		x 45
		y 55
		noun N_HORSE
		approachX 44
		approachY 113
		view 220
		loop 6
		cycleSpeed 30
		detailLevel 2
	)
)

(instance smithyDoor of Prop
	(properties
		x 30
		y 86
		noun N_SMITHY_DOOR
		view 220
		loop 1
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_FIREGOING)
			)
			(V_DO
				(messager say: noun theVerb C_FIREGOING)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bulletHole of Feature
	(properties
		x 161
		y 80
		noun N_BULLET_HOLE
		nsTop 77
		nsLeft 160
		nsBottom 83
		nsRight 163
		sightAngle 40
		approachX 161
		approachY 80
	)
)

(instance sidewalk of Feature
	(properties
		x 161
		y 80
		noun N_SIDEWALK
		sightAngle 40
		approachX 161
		approachY 80
	)
)

(instance hay of Feature
	(properties
		x 161
		y 80
		noun N_HAY
		sightAngle 40
		approachX 161
		approachY 80
	)
)

(instance wagon of Feature
	(properties
		x 256
		y 45
		noun N_WAGON
		approachX 254
		approachY 53
	)
)

(instance anvilFtr of Feature
	(properties
		x 7
		y 95
		noun N_ANVIL
	)
)

(instance firepit of Feature
	(properties
		x 7
		y 95
		noun N_COALS
		approachX 6
		approachY 93
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_FIREGOING)
			)
			(V_DO
				(messager say: noun theVerb C_FIREGOING)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blacksmith of Feature
	(properties
		x 50
		y 46
		noun N_BLACKSMITH
		sightAngle 40
		approachX 53
		approachY 102
		approachDist 63
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_FIREGOING)
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
		approachX 72
		approachY 151
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
)

(instance balanceStreet of Feature
	(properties
		x 269
		y 74
		noun N_BALANCE_STREET
		sightAngle 40
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
		noun N_MAIN_STREET
		sightAngle 40
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

(instance bankDoor of View
	(properties
		x 190
		y 93
		noun N_BANK
		approachX 204
		approachY 100
		view 220
		cel 2
	)
)

(instance horseSound of FPSound
	(properties
		flags mNOPAUSE
	)
)

(instance smithieSound of FPSound
	(properties
		flags mNOPAUSE
	)
)

(instance shakeSound of FPSound
	(properties
		flags mNOPAUSE
		number 2222
		loop -1
	)
)
