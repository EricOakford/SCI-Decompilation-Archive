;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use eureka)
(use Inset)
(use Scaler)
(use Osc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm250 0
)

(local
	local0
	local1
	local2
	local3 =  1
	local4 =  1
	[local5 5] = [25 64 77 94 111]
	[local10 5] = [25 75 90 107 127]
)
(procedure (localproc_0108)
	(cond 
		((Btst 84)
			(if (!= (theMusic1 number?) 42)
				(theMusic1 number: 42 loop: -1 play: 127)
			)
		)
		((Btst 61)
			(if (!= (theMusic1 number?) 20)
				(theMusic1 number: 20 loop: -1 play: 127)
			)
		)
		(else (theMusic1 number: 101 loop: -1 play: 127))
	)
)

(procedure (localproc_0170 param1)
	(return
		(cond 
			((InRect 83 [local5 1] 110 [local10 1] param1) (return 1))
			((InRect 83 [local5 2] 110 [local10 2] param1) (return 2))
			((InRect 83 [local5 3] 110 [local10 3] param1) (return 3))
			((InRect 83 [local5 4] 110 [local10 4] param1) (return 4))
			(else (return -1))
		)
	)
)

(instance rm250 of Rm
	(properties
		noun 10
		picture 44
		style $000a
		vanishingX 20
		vanishingY 110
	)
	
	(method (init &tmp temp0)
		(curRoom setRegions: 210)
		(LoadMany 128 251 266)
		(NormalEgo 0)
		(ego init: setScale: Scaler 134 81 164 134 edgeHit: 0)
		(cliffySuit init:)
		(otherSuit init:)
		(oxyTank init:)
		(console init: setOnMeCheck: 1 2)
		(elevatorTop init:)
		(elevatorBottom init:)
		(pod init:)
		(cliffy init:)
		(maskDoor init: stopUpd:)
		(cDoorF init:)
		(bayDoorsF init: setOnMeCheck: 1 16)
		(switch prevRoomNum
			(north
				(ego posn: 288 135 setHeading: 180)
				(self setScript: sOpenElev)
			)
			(801
				(ego posn: -50 -50)
				(podRmDoors init: setCel: (podRmDoors lastCel:))
				(self setScript: dropOffCliffy)
			)
			(else 
				(ego posn: 288 135 setHeading: 180)
				(self setScript: sOpenElev)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						152
						131
						126
						134
						183
						153
						114
						163
						49
						141
						1
						144
						2
						185
						157
						187
						295
						151
						268
						152
						231
						150
						153
						131
					yourself:
				)
		)
		(super init:)
		(localproc_0108)
		(walkHandler addToFront: turboLift)
	)
	
	(method (dispose)
		(walkHandler delete: turboLift)
		(super dispose:)
	)
)

(instance sPopUpConsole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(theIconBar disable: 0 3 1 5 4 6)
				(= seconds 2)
				(= register 0)
			)
			(1
				(cast eachElementDo: #stopUpd)
				(curRoom setInset: popUpConsole self)
				(theGame setCursor: 982 1 88 (+ [local5 1] 5))
			)
			(2 (= seconds 2))
			(3
				(switch register
					(1
						(if (cast contains: cliffy)
							(messager say: 11 0 0 0 self)
						else
							(= next sDoRedButton)
							(self dispose:)
						)
					)
					(2
						(self setScript: sElevatorDoors self)
					)
					(3
						(self setScript: sRotatePod self)
					)
					(4
						(self setScript: sIntercom self)
					)
					(else  (= cycles 1))
				)
			)
			(4
				(theIconBar enable: 0 3 5 4 1 6)
				(if (cast contains: cliffy) (cliffy startUpd:))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenElev of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 288 138)
				(= cycles 1)
			)
			(1
				(theMusic2 number: 127 loop: 1 play:)
				(= cycles 1)
			)
			(2
				(elevLights init: setCycle: End self)
			)
			(3
				(theMusic2 number: 241 setLoop: 1 play: self)
			)
			(4
				(elevLights dispose:)
				(self setScript: sElevatorDoors self)
			)
			(5
				(ego setMotion: MoveTo 277 154 self)
			)
			(6
				(self setScript: sElevatorDoors self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 288 138 self)
			)
			(1
				(ego setHeading: 180)
				(= seconds 1)
			)
			(2
				(self setScript: sElevatorDoors self)
			)
			(3
				(elevLights init: setCycle: Beg self)
				(theMusic2 number: 127 loop: 1 play:)
			)
			(4
				(curRoom newRoom: 225)
				(self dispose:)
			)
		)
	)
)

(instance sRogPushButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				(rogThink init:)
				(= seconds 1)
			)
			(1
				(rogThink setCycle: End self)
				(rogArm init: cycleSpeed: 20 setCycle: Osc 1 self)
			)
			(2 0)
			(3
				(rogArm dispose:)
				(rogThink setCycle: Beg self)
			)
			(4 (self dispose:))
		)
	)
)

(instance sDoButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sPopUpConsole register: local0)
				(= seconds 2)
			)
			(1
				(popUpConsole dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenPodDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 118)
				(podDoor init: cel: 0 setCycle: End self)
				(theMusic2 number: 217 loop: 1 play:)
			)
			(1
				(podDoor setLoop: 1)
				(= cycles 3)
			)
			(2
				(podDoor setLoop: 1 cel: 0 setCycle: End self)
				(theMusic2 number: 217 loop: 1 play:)
			)
			(3
				(podDoor stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sClosePodDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(podDoor setLoop: 1 cel: 10 setCycle: Beg self)
				(theMusic2 number: 217 loop: 1 play:)
			)
			(1 (= cycles 1))
			(2
				(podDoor setLoop: 0 cel: 12 setCycle: Beg self)
				(theMusic2 number: 217 loop: 1 play:)
			)
			(3
				(podDoor dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sRotatePod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (cast contains: pod)
					(= cycles 1)
				else
					(messager say: 16 0 0 1 self)
					(= state 4)
				)
			)
			(1
				(if (== (pod cel?) 4)
					(Bclr 118)
					(self setScript: sClosePodDoor self)
					(= state 2)
				else
					(pod view: 254 loop: 0 cel: 0 setCycle: End self)
				)
			)
			(2
				(pod stopUpd:)
				(self setScript: sOpenPodDoor self)
				(Bset 118)
				(= state 3)
			)
			(3
				(pod
					view: 254
					loop: 0
					cel: (pod lastCel:)
					setCycle: Beg self
				)
			)
			(4
				(pod
					view: 251
					loop: 1
					cel: (if (pod cel?) 4 else 3)
					stopUpd:
				)
				(= cycles 1)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetInPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (== eurekaCurLocation 14) (SolvePuzzle 195 300))
				(ego
					view: 267
					setLoop: 0
					cel: 0
					x: 142
					y: 156
					setScale: 0
					setCycle: End self
				)
			)
			(2 (= seconds 2))
			(3
				(self setScript: sClosePodDoor self)
			)
			(4
				(if (not local4)
					(self setScript: sElevatorDoors self)
				else
					(= cycles 1)
				)
			)
			(5
				(podRmDoors init: cycleSpeed: 10 setCycle: End self)
				(= local3 0)
			)
			(6
				(pod
					view: 254
					cel: 0
					setLoop: 1
					x: 161
					y: 154
					setCycle: End self
				)
			)
			(7
				(if (== eurekaCurLocation 15)
					(curRoom newRoom: 801)
				else
					(Bset 88)
					(curRoom newRoom: 802)
				)
				(self dispose:)
			)
		)
	)
)

(instance sIntercom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 3 4 0 1 self))
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoRedButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(self setScript: sRogPushButton self)
			)
			(2
				(podRmDoors init: cycleSpeed: 16 setCycle: End self)
				(= local3 0)
			)
			(3 (= cycles 1))
			(4
				(theMusic1 number: 276 loop: -1 play:)
				(= cycles 1)
			)
			(5
				(rogThink loop: 5 cel: 0 posn: 246 126 setCycle: Fwd self)
				(theMusic2 number: 102 loop: 1 play:)
				(= seconds 4)
			)
			(6
				(rogThink
					setLoop: 5
					setCel: 8
					setPri: 6
					setCycle: 0
					moveSpeed: 0
					setStep: 20
					setScale: Scaler 134 75 164 110
					setMotion: MoveTo 35 110 self
				)
			)
			(7
				(podRmDoors setCycle: Beg self)
			)
			(8
				(podRmDoors dispose:)
				(= seconds 2)
			)
			(9
				(theMusic1 stop:)
				(rogThink dispose:)
				(EgoDead 10)
				(self dispose:)
			)
		)
	)
)

(instance sElevatorDoors of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(if local4
					(elevatorBottom startUpd:)
					(elevatorTop
						posn: 249 88
						ignoreActors: 0
						setMotion: MoveTo 249 45 self
					)
					(turboLift init:)
					(= local4 0)
				else
					(elevatorTop
						posn: 249 45
						ignoreActors: 0
						setMotion: MoveTo 249 88 self
					)
					(elevatorBottom ignoreActors: 0)
					(turboLift dispose:)
					(= local4 1)
				)
			)
			(2
				(if local4 (theMusic2 number: 233 loop: 1 play:))
				(elevatorTop stopUpd:)
				(elevatorBottom stopUpd:)
				(if (!= client sOpenElev) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance dropOffCliffy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset fGotCliffy)
				(ego stopUpd:)
				(pod setCycle: End self)
			)
			(1
				(podRmDoors setCycle: Beg self)
			)
			(2
				(podRmDoors dispose:)
				(= local3 1)
				(pod
					view: 254
					cel: 0
					setLoop: 0
					x: 161
					y: 154
					cycleSpeed: 20
					setCycle: End
				)
				(cliffy
					x: 150
					y: 138
					setLoop: 1
					cel: 0
					setCycle: 0
					setStep: 6 6
					setMotion: MoveTo 150 166 self
				)
			)
			(3
				(theMusic2 number: 102 loop: 1 play:)
				(cliffy setCycle: End self)
			)
			(4
				(cliffy setLoop: 2 cel: 0 setCycle: End self)
			)
			(5
				(cliffy view: 251 loop: 1 cel: 5 stopUpd:)
				(pod view: 251 setLoop: 1 cel: 4 stopUpd:)
				(self setScript: sOpenPodDoor self)
			)
			(6
				(ego
					view: 267
					setLoop: 0
					cel: 11
					x: 142
					y: 156
					setScale: 0
					setCycle: Beg self
				)
			)
			(7
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 114 164 176 155 182 177 121 177
							yourself:
						)
				)
				(NormalEgo 0 7)
				(ego
					setScale: Scaler 134 81 164 134
					posn: 147 156
					setMotion: MoveTo 182 156 self
				)
			)
			(8 (ego setHeading: 225 self))
			(9
				(messager say: 12 0 0 0 self)
			)
			(10
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getTank of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(oxyTank view: 266 loop: 2 cel: 0 setCycle: End self)
				(theMusic2 number: 103 loop: 1 play:)
				(= global137 3)
			)
			(1
				(ego view: 267 loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(2
				(SolvePuzzle 190 25)
				(oxyTank setCel: 2 loop: 1 view: 251)
				(ego get: 9 setCycle: End self)
			)
			(3
				(NormalEgo 0 6)
				(oxyTank stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance returnTank of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 267 loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(1
				(ego put: 9 setCycle: End self)
				(oxyTank startUpd: view: 266 loop: 2 cel: 8)
			)
			(2
				(theMusic2 number: 103 loop: 1 play:)
				(oxyTank setCycle: Beg self)
			)
			(3
				(NormalEgo 0 6)
				(oxyTank view: 251 loop: 1 cel: 6 stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getMask of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(1
				(ego view: 243 loop: 2 cel: 0 setCycle: End self)
			)
			(2
				(oxygenMask init:)
				(theMusic2 number: 103 loop: 1 play:)
				(maskDoor setCycle: End self)
				(ego setCycle: Beg self)
			)
			(3)
			(4
				(ego view: 267 loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(5
				(SolvePuzzle 182 5)
				(oxygenMask setLoop: 4)
				(ego setCycle: End self)
			)
			(6
				(theMusic2 number: 103 loop: 1 play:)
				(maskDoor setCycle: Beg self)
			)
			(7
				(NormalEgo 0 6)
				(oxygenMask dispose:)
				(maskDoor stopUpd:)
				(ego get: 21)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(1
				(theMusic2 number: 4800 loop: -1 play:)
				(cDoor init: setCycle: End self)
			)
			(2
				(chick
					init:
					posn: 1 117
					setMotion: MoveTo 67 157 self
					setCycle: Fwd
				)
			)
			(3
				(cDoor setCycle: Beg)
				(chick setMotion: MoveTo 193 186 self)
			)
			(4
				(cDoor dispose:)
				(chick dispose:)
				(theMusic2 stop:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance bang of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(< (theGame detailLevel:) (cliffy detailLevel:))
					(cliffy stopUpd:)
					(-- state)
				else
					(cliffy startUpd:)
				)
				(= cycles 1)
			)
			(1
				(cliffy cel: 0 setCycle: End self)
			)
			(2
				(= state (- state 3))
				(if (not (curRoom script?))
					(theMusic2 number: 230 loop: 1 play: self)
				else
					(= cycles 1)
				)
			)
		)
	)
)

(instance oxygenMask of View
	(properties
		x 183
		y 90
		approachX 172
		approachY 141
		view 251
		loop 3
		priority 4
		signal $4010
	)
)

(instance maskDoor of Prop
	(properties
		x 183
		y 90
		noun 15
		approachX 172
		approachY 141
		view 251
		loop 2
		priority 5
		signal $5011
	)
	
	(method (init)
		(self approachVerbs: 4 1)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (ego has: 21)
					(messager say: 15 4 1 0)
				else
					(curRoom setScript: getMask)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cliffy of Actor
	(properties
		x 10
		y 143
		noun 17
		view 191
		loop 2
		cel 2
		scaleSignal $0001
		detailLevel 3
	)
	
	(method (init)
		(cond 
			((== prevRoomNum 801)
				(self
					view: 268
					loop: 1
					cel: 0
					posn: -50 -38
					ignoreActors: 1
					setPri: 13
				)
				(= global130 3)
				(super init: &rest)
			)
			((== global130 3)
				(self setScale: Scaler 134 81 164 134 setScript: bang)
				(super init: &rest)
			)
		)
	)
)

(instance cDoor of Prop
	(properties
		x 4
		y 102
		view 251
		loop 5
		priority 8
		signal $4010
	)
)

(instance chick of Actor
	(properties
		y 116
		yStep 4
		view 970
		signal $4800
		xStep 6
	)
)

(instance podRmDoors of Prop
	(properties
		x 38
		y 83
		noun 1
		view 266
		loop 1
	)
	
	(method (setCycle cType)
		(if (and cType (not cel))
			(theMusic2 number: 108 loop: 1 play:)
		)
		(super setCycle: cType &rest)
	)
)

(instance podDoor of Prop
	(properties
		x 127
		y 161
		z 60
		approachX 142
		approachY 156
		view 262
		priority 10
		signal $4010
	)
	
	(method (init)
		(self approachVerbs: 4 setPri: 10)
		(if (Btst 118)
			(self setLoop: 1 cel: 10)
		else
			(self setLoop: 0 cel: 0)
		)
		(if (not (Btst 88)) (super init:))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(switch eurekaCurLocation
					(15
						(if (Btst 87)
							(messager say: 13 0 0 0)
						else
							(curRoom setScript: sGetInPod)
						)
					)
					(14
						(curRoom setScript: sGetInPod)
					)
					(else  (messager say: 14 0 0 0))
				)
			)
			(else  (super doVerb: &rest))
		)
	)
)

(instance pod of Prop
	(properties
		x 161
		y 154
		noun 5
		view 251
		loop 1
		cel 3
		priority 10
		signal $4011
		cycleSpeed 12
	)
	
	(method (init)
		(cond 
			((== prevRoomNum 801)
				(self view: 268 loop: 4 cel: 0 x: 63 y: 139)
				(super init:)
			)
			(
			(or (Btst 88) (and (Btst 84) (!= (eureka state?) 3))) (self dispose:))
			((Btst 118)
				(self view: 251 loop: 1 cel: 4)
				(super init:)
				(podDoor init:)
			)
			(else (self stopUpd:) (super init:))
		)
	)
	
	(method (setCycle cType)
		(if (and argc cType)
			(theMusic2 number: 106 loop: 1 play:)
		)
		(super setCycle: cType &rest)
	)
)

(instance elevatorTop of Actor
	(properties
		x 249
		y 88
		noun 4
		view 251
		priority 10
		signal $4811
	)
	
	(method (setMotion mType)
		(if (and argc mType)
			(theMusic2 number: 108 loop: 1 play:)
		)
		(super setMotion: mType &rest)
	)
)

(instance elevatorBottom of View
	(properties
		x 251
		y 128
		noun 4
		view 251
		cel 1
		priority 9
		signal $4811
	)
	
	(method (doit)
		(self posn: x (+ 128 (/ (- 88 (elevatorTop y?)) 2)))
		(super doit:)
	)
)

(instance elevLights of Prop
	(properties
		x 273
		y 97
		view 266
		priority 15
		signal $0010
	)
)

(instance rogThink of Actor
	(properties
		x 240
		y 170
		view 267
		loop 2
		priority 12
		signal $4010
	)
)

(instance rogArm of Prop
	(properties
		x 226
		y 112
		view 267
		loop 3
		priority 13
		signal $4010
	)
)

(instance oxyTank of Prop
	(properties
		x 167
		y 95
		noun 7
		approachX 158
		approachY 133
		view 251
		loop 1
		priority 6
		signal $4010
	)
	
	(method (init)
		(self
			cel: (if (ego has: 9) 2 else 6)
			approachVerbs: 4 1 25
		)
		(super init: &rest)
		(self stopUpd:)
	)
	
	(method (doVerb theVerb)
		(if (== (self cel?) 2)
			(self noun: 7)
		else
			(self noun: 8)
		)
		(switch theVerb
			(4
				(if (ego has: 9)
					(curRoom setScript: returnTank)
				else
					(curRoom setScript: getTank)
				)
			)
			(25
				(curRoom setScript: returnTank)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theKey of View
	(properties
		x 83
		y 64
		sightAngle 360
		view 252
		loop 1
		cel 1
		priority 15
		signal $0010
	)
	
	(method (show param1)
		(self y: [local5 param1] setLoop: param1 setCel: 0)
		(super show: &rest)
	)
)

(instance cDoorF of Feature
	(properties
		y 98
		nsTop 98
		nsBottom 125
		nsRight 8
		approachY 150
	)
	
	(method (init)
		(self approachVerbs: 4)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: cStuff)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance console of Feature
	(properties
		x 274
		y 225
		z 50
		noun 9
		onMeCheck $0002
		approachX 240
		approachY 170
		approachDist 2
	)
	
	(method (init)
		(self approachVerbs: 4 1)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sPopUpConsole)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cliffySuit of Feature
	(properties
		x 212
		y 117
		noun 2
		nsTop 95
		nsLeft 205
		nsBottom 139
		nsRight 220
		sightAngle 40
	)
)

(instance otherSuit of Feature
	(properties
		x 233
		y 117
		noun 6
		nsTop 95
		nsLeft 228
		nsBottom 139
		nsRight 239
		sightAngle 40
	)
)

(instance bayDoorsF of Feature
	(properties
		x 70
		y 110
		noun 1
		onMeCheck $0010
	)
)

(instance turboLift of Feature
	(properties
		x 277
		y 104
		noun 4
		nsTop 60
		nsLeft 240
		nsBottom 148
		nsRight 315
		sightAngle 40
		approachX 285
		approachY 152
		approachDist 55
	)
	
	(method (init)
		(turboLift approachVerbs: 3 4)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (not local4) (curRoom setScript: sExitNorth))
			)
			(4
				(if (not local4) (curRoom setScript: sExitNorth))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance popUpConsole of Inset
	(properties
		view 252
		x 60
		y 25
		disposeNotOnMe 1
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theKey init: show: 1 stopUpd:)
		(= local0 1)
	)
	
	(method (doit)
		(if local1
			(if local0 (theMusic2 number: 124 loop: 1 play:))
			(= local1 0)
			(self setScript: sDoButton)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theKey dispose:)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (User controls?)
			(= temp0 (event message?))
			(cond 
				(
					(or
						(& (event type?) evKEYBOARD)
						(& (event type?) evJOYSTICK)
					)
					(switch temp0
						(13
							(= local1 1)
							(event claimed: 1)
							(return)
						)
						(27
							(= local1 1)
							(= local0 0)
							(event claimed: 1)
							(return)
						)
						(5
							(= local0 (if (>= local0 4) 1 else (++ local0)))
							(theKey show: local0)
							(theGame setCursor: 982 1 88 (+ [local5 local0] 5))
							(event claimed: 1)
							(return)
						)
						(1
							(= local0 (if (<= local0 1) 4 else (-- local0)))
							(theKey show: local0)
							(theGame setCursor: 982 1 88 (+ [local5 local0] 5))
							(event claimed: 1)
							(return)
						)
						(else 
							(super handleEvent: event &rest)
						)
					)
				)
				(
					(and
						(& (event type?) evMOUSEBUTTON)
						(!= (= local0 (localproc_0170 event)) -1)
						(not (event modifiers?))
					)
					(theGame handsOff:)
					(theKey show: local0)
					(= local1 1)
					(event claimed: 1)
				)
				(else (super handleEvent: event &rest))
			)
		else
			(super handleEvent: event &rest)
		)
	)
)
