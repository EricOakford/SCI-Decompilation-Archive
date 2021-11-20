;;; Sierra Script 1.0 - (do not remove this comment)
(script# 641)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm641 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm641 of GloryRm
	(properties
		modNum 640
		picture 640
	)
	
	(method (init)
		(theGame handsOff:)
		(ego setScaler: Scaler 136 78 189 117)
		(RemapColors 2 253 140)
		(RemapColors 2 254 60)
		(if (== prevRoomNum 810)
			(pDoor setPri: 112)
			(theMusic doSongs: 630 631 632)
			(switch battleResult
				(1
					(aGhost init: ignoreActors: 1 setCycle: Fwd)
					(ego posn: 160 170)
					(curRoom setScript: sEgosDead)
				)
				(2
					(ego posn: 160 170 normalize:)
					(Bset 185)
					(theGame handsOn:)
				)
				(else 
					(aGhost init: setCycle: End)
					(ego posn: 276 156)
					(Bclr 394)
					(curRoom setScript: sOpenTheDoor)
				)
			)
			(ego init: normalize:)
		else
			(Bclr 394)
			(ego
				posn: 298 116
				init:
				normalize:
				setPri: 64
				setScript: sComeOnIn
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-300
						152
						-300
						0
						619
						0
						619
						146
						317
						137
						295
						137
						287
						148
						263
						148
						253
						135
						231
						135
						208
						147
						191
						147
						171
						153
						112
						153
						103
						148
						69
						139
						77
						125
						38
						125
					yourself:
				)
		)
		(super init: &rest)
		((pFlame new:) setCel: 0 posn: 46 70 setCycle: Fwd init:)
		((pFlame new:) setCel: 1 posn: 51 69 setCycle: Fwd init:)
		((pFlame new:) setCel: 2 posn: 55 67 setCycle: Fwd init:)
		((pFlame new:) setCel: 0 posn: 58 68 setCycle: Fwd init:)
		((pFlame new:) setCel: 2 posn: 62 72 setCycle: Fwd init:)
		((pFlame new:)
			setCel: 1
			posn: 217 81
			setCycle: Fwd
			init:
		)
		((pFlame new:)
			setCel: 0
			posn: 220 77
			setCycle: Fwd
			init:
		)
		((pFlame new:)
			setCel: 1
			posn: 224 76
			setCycle: Fwd
			init:
		)
		((pFlame new:)
			setCel: 0
			posn: 227 78
			setCycle: Fwd
			init:
		)
		((pFlame new:)
			setCel: 0
			posn: 233 79
			setCycle: Fwd
			init:
		)
		(if (or (Btst 394) (Btst 185))
			(if (Btst 394) (= local1 1))
			(pChest setCel: (if (Btst 394) 4 else 0) init:)
		)
		(pDoor approachVerbs: 4 32 init:)
		(vTheBed signal: (| (vTheBed signal?) $1000) init:)
		(vTheRug ignoreActors: init:)
		(vLeftWindow ignoreActors: init:)
		(vRightDoor ignoreActors: approachVerbs: 4 32 init:)
		(doorTeller init: vRightDoor 640 2 155)
		(vLeftCandle ignoreActors: init:)
		(vRightCandle ignoreActors: init:)
		(fChest init: approachVerbs: 4)
		(fSteps init: approachVerbs: 4)
		(fBedHead init: approachVerbs: 4)
		(fCurtain1 init: approachVerbs: 4)
		(fCurtain2 init: approachVerbs: 4)
		(if
			(and
				(Btst 185)
				(== prevRoomNum 810)
				(== battleResult 2)
			)
			(messager say: 3 6 32 0 0 640)
		)
		(if (and (!= prevRoomNum 810) (not (Btst 185)))
			(theGame save: 1)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 0)
			)
			(1
				(messager say: 0 1 0 0 0 641)
			)
			(82
				(if (Btst 185)
					(if (Btst 394)
						(messager say: 6 80 19 0 0 640)
					else
						(curRoom setScript: sOpenTheChest)
					)
				else
					(curRoom setScript: sGhostHere)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(doorSound play:)
				(pDoor setCycle: End self)
			)
			(2
				(pDoor setPri: 112)
				(ego setPri: -1 setMotion: PolyPath 304 149 self)
			)
			(3
				(doorCloseSound play:)
				(pDoor setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPeepingTom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 4 setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(1
				(messager say: 2 155 27 1 self 640)
			)
			(2 (= seconds 3))
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenTheDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(doorSound play:)
				(if
				(and (not (Btst 213)) (not (== prevRoomNum 810)))
					(messager say: 1 4 3 1 0 640)
					(squeakSound play:)
				)
				(pDoor setCycle: End self)
			)
			(2
				(squeakSound dispose:)
				(doorSound dispose:)
				(pDoor setPri: 75)
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 2) self)
			)
			(3 (curRoom newRoom: 623))
		)
	)
)

(instance sCastOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 20)
				(= seconds 2)
			)
			(1
				(doorSound play:)
				(if
				(and (not (Btst 213)) (not (== prevRoomNum 810)))
					(messager say: 1 4 3 1 0 640)
					(squeakSound play:)
				)
				(pDoor setCycle: End self)
			)
			(2
				(squeakSound dispose:)
				(doorSound dispose:)
				(pDoor setPri: 75)
				(ego
					setMotion: PolyPath (pDoor approachX?) (pDoor approachY?) self
				)
			)
			(3 (curRoom newRoom: 623))
		)
	)
)

(instance sPickLock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego trySkill: 9 castleDoorDifficulty)
				(ego trySkill: 9 castleDoorDifficulty)
				(if (== (ego trySkill: 9 castleDoorDifficulty) 1)
					(theGame handsOff:)
					(messager say: 1 42 1 1 self 640)
				else
					(messager say: 1 42 2 1 0 640)
					(self dispose:)
				)
			)
			(1
				(if (Btst 213)
					(self cue:)
				else
					(squeakSound play:)
					(messager say: 1 4 3 1 0 640)
					(self cue:)
				)
			)
			(2
				(doorSound play:)
				(pDoor setCycle: End self)
			)
			(3
				(if (>= castleDoorDifficulty 300)
					(= castleDoorDifficulty 300)
				else
					(++ castleDoorDifficulty)
				)
				(= ticks 24)
			)
			(4
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 2) self)
				(squeakSound dispose:)
				(doorSound dispose:)
			)
			(5 (curRoom newRoom: 623))
		)
	)
)

(instance sEgosDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1 (EgoDead 24 640 43 End 852))
		)
	)
)

(instance sOpenTheChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 20)
				(cond 
					((Btst 187) (client setScript: sAlreadyDone))
					(local1 (self changeState: 4))
					(else (self cue:))
				)
			)
			(1
				(if (< (ego distanceTo: fChest) 50) (= local0 1))
				(if (not local2)
					(explosiveSound play:)
					(pExplosive init: setCycle: End self)
				else
					(self cue:)
				)
			)
			(2
				(if (not local2) (pExplosive dispose:))
				(pChest
					init:
					signal: (| (pChest signal?) $0001)
					setCycle: End self
				)
			)
			(3
				(if (not local2)
					(if local0 (ego takeDamage: 50))
					(messager say: 3 6 16 0 self 640)
				else
					(self cue:)
				)
			)
			(4
				(if (<= [egoStats 17] 0)
					(EgoDead 16 640)
				else
					(ego setMotion: PolyPath 144 140 self)
				)
			)
			(5
				(= local1 1)
				(ego normalize:)
				(messager say: 6 4 20 1 self 640)
				(ego get: 0 25)
				(ego get: 3 1)
			)
			(6
				(Bset 187)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAlreadyDone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (pChest cel?) 4)
					(self cue:)
				else
					(pChest
						signal: (| (pChest signal?) $0001)
						setCycle: End self
					)
				)
			)
			(1
				(ego setMotion: PolyPath 144 140 self)
			)
			(2
				(ego normalize:)
				(pChest signal: (& (pChest signal?) $fffe))
				(messager say: 6 4 19 1 0 640)
				(self cue:)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenGhostHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< (ego distanceTo: fChest) 50) (= local0 1))
				(if (not local2)
					(explosiveSound play:)
					(pExplosive init: setCycle: End self)
				else
					(self cue:)
				)
			)
			(1
				(if (not local2) (pExplosive dispose:))
				(pChest
					init:
					signal: (| (pChest signal?) $0001)
					setCycle: End self
				)
			)
			(2
				(if (not local2)
					(if local0 (ego takeDamage: 50))
					(messager say: 3 6 16 0 self 640)
				else
					(self cue:)
				)
			)
			(3
				(Bset 394)
				(self setScript: sGhostHere)
			)
		)
	)
)

(instance sGhostHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ghostSound play:)
				(aGhost init: setLoop: 3 1 setCel: 0 setCycle: End self)
			)
			(1
				(= global365 850)
				(= monsterHealth 300)
				(aGhost
					setCel: 0
					setLoop: 4 1
					setCycle: Fwd
					setMotion: MoveTo (- (ego x?) 5) (- (ego y?) 20) self
				)
			)
			(2
				(ghostSound stop:)
				(curRoom newRoom: 810)
			)
		)
	)
)

(instance sDoTheChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local1
					(messager say: 6 80 19 1 self 640)
				else
					(messager say: 6 4 9 1 self 640)
				)
			)
			(1
				(chestTeller init: pChest 640 2 167)
				(chestTeller doVerb: 4)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance aGhost of Actor
	(properties
		x 133
		y 99
		priority 174
		fixPriority 1
		view 686
		loop 3
	)
)

(instance pFlame of Prop
	(properties
		noun 15
		modNum 640
		view 699
		loop 6
		detailLevel 2
	)
)

(instance pExplosive of Prop
	(properties
		x 144
		y 127
		priority 174
		fixPriority 1
		view 21
		loop 9
	)
)

(instance pChest of Prop
	(properties
		noun 6
		modNum 640
		sightAngle 180
		x 113
		y 133
		z 12
		priority 146
		fixPriority 1
		view 686
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(80 (fChest doVerb: theVerb))
			(4
				(if cel
					(curRoom setScript: sOpenTheChest)
				else
					(curRoom setScript: sDoTheChest)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pDoor of Prop
	(properties
		modNum 640
		approachX 309
		approachY 165
		x 261
		y 51
		priority 112
		fixPriority 1
		view 695
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else 
				(vRightDoor doVerb: theVerb)
			)
		)
	)
)

(instance vTheBed of View
	(properties
		noun 19
		modNum 640
		approachX 299
		approachY 123
		x 82
		y 124
		z 124
		priority 108
		fixPriority 1
		view 686
	)
)

(instance vTheRug of View
	(properties
		noun 33
		modNum 640
		x 10
		y 123
		priority 53
		fixPriority 1
		view 692
	)
)

(instance vLeftWindow of View
	(properties
		noun 16
		modNum 640
		x 6
		y 27
		view 697
		loop 2
	)
)

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 303
		approachY 120
		x 261
		y 53
		z 4
		priority 108
		fixPriority 1
		view 695
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(32
				(Bset 213)
				(messager say: 1 32 0 0 0 640)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vLeftCandle of View
	(properties
		noun 15
		modNum 640
		x 80
		y 68
		view 699
		loop 1
	)
)

(instance vRightCandle of View
	(properties
		noun 15
		modNum 640
		x 229
		y 77
		view 699
	)
)

(instance fChest of Feature
	(properties
		noun 6
		modNum 640
		nsLeft 116
		nsTop 116
		nsRight 178
		nsBottom 148
		sightAngle 180
		approachX 172
		approachY 154
		x 147
		y 132
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(if (Btst 185)
					(if (Btst 394)
						(messager say: 6 80 19 0 0 640)
					else
						(curRoom setScript: sOpenTheChest)
					)
				else
					(curRoom setScript: sOpenGhostHere)
				)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(if (Btst 185)
					(curRoom setScript: sDoTheChest)
				else
					(curRoom setScript: sGhostHere)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fSteps of Feature
	(properties
		noun 8
		modNum 640
		nsLeft 283
		nsTop 116
		nsRight 319
		nsBottom 137
		sightAngle 180
		x 301
		y 126
	)
)

(instance fBedHead of Feature
	(properties
		noun 19
		modNum 640
		nsLeft 117
		nsTop 20
		nsRight 206
		nsBottom 80
		sightAngle 180
		x 161
		y 50
	)
)

(instance fCurtain1 of Feature
	(properties
		noun 18
		modNum 640
		nsLeft 87
		nsRight 114
		nsBottom 94
		sightAngle 180
		x 100
		y 47
	)
)

(instance fCurtain2 of Feature
	(properties
		noun 18
		modNum 640
		nsLeft 209
		nsRight 235
		nsBottom 123
		sightAngle 180
		x 222
		y 61
	)
)

(instance chestTeller of Teller
	(properties
		modNum 640
		actionVerb 4
	)
	
	(method (doVerb)
		(if (pChest cel?)
			(pChest doVerb: &rest)
		else
			(super doVerb: &rest)
		)
	)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(sDoTheChest cue:)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(switch iconValue
			(13
				(theGame handsOn:)
				(if local2
					(self clean:)
					(curRoom setScript: sOpenTheChest)
				else
					(if ((ScriptID 648 0) init: show: dispose:)
						(self clean:)
						(= local2 1)
						(DisposeScript 648)
						(curRoom setScript: sOpenTheChest)
					else
						(self clean:)
						(if (Btst 182)
							(Bclr 182)
							(curRoom setScript: sOpenTheChest)
						)
					)
					(DisposeScript 648)
				)
			)
			(25
				(if (== (ego trySkill: 0 325) 1)
					(self clean:)
					(curRoom setScript: sOpenTheChest)
				else
					(super sayMessage: 3 6 17 &rest)
					(theGame handsOn:)
				)
			)
			(26
				(self clean:)
				(curRoom setScript: sOpenTheChest)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				13
				(if (and (ego has: 24) (Btst 242))
					(not local1)
				else
					0
				)
				4
				(not local1)
				26
				(if [egoStats 9] (not local1) else 0)
				25
				(not local1)
		)
	)
)

(instance doorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(4
				(if (== heroType 2)
					(super sayMessage: 3 6 9 &rest)
				else
					(self clean:)
					(curRoom setScript: sOpenTheDoor)
				)
			)
			(7
				(self clean:)
				(curRoom setScript: sPickLock)
			)
			(27
				(self clean:)
				(curRoom setScript: sPeepingTom)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				13
				(if (== heroType 2) (Btst 242) else 0)
				7
				(if [egoStats 9] (ego has: 24) else 0)
		)
	)
)

(instance squeakSound of Sound
	(properties
		number 143
	)
)

(instance doorSound of Sound
	(properties
		number 972
	)
)

(instance doorCloseSound of Sound
	(properties
		number 973
	)
)

(instance ghostSound of Sound
	(properties
		number 852
	)
)

(instance explosiveSound of Sound
	(properties
		number 971
	)
)
