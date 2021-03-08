;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include sci.sh)
(use Main)
(use eureka)
(use Scaler)
(use Osc)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm200 0
)

(local
	[local0 4] = [0 2 4 7]
	[local4 4] = [1 3 4 6]
	local8
	local9
)
(procedure (localproc_00d4)
	(floHead2 init: setLoop: 0 setScript: (sRandomScr new:))
	(flo
		view: 185
		setLoop: 1
		setCycle: End
		setScript: (sRandomScr new:)
	)
)

(procedure (localproc_0112)
	(droole
		view: 186
		loop: 1
		setCycle: End
		setScript: (sRandomScr new:)
	)
)

(procedure (localproc_0135)
	(droole
		view: 186
		setLoop: 0
		cel: 1
		cycleSpeed: 6
		setCycle: Osc
	)
)

(procedure (localproc_0156)
	(floHead2 dispose:)
	(flo view: 185 setLoop: 2 cel: 1 setCycle: Osc)
)

(procedure (localproc_0179)
	(ego
		view: 181
		setLoop: 7
		cel: 0
		posn: 157 146
		setCycle: Osc
	)
)

(procedure (localproc_019e param1)
	(ego
		view: 181
		loop: 2
		cel: 0
		posn: 157 146
		setCycle: End (if argc param1 else 0)
	)
)

(procedure (localproc_01cc)
	(ego
		view: 181
		setPri: -1
		setLoop: 2
		setCel: 7
		posn: 157 146
	)
)

(procedure (localproc_01f0 param1)
	(ego
		view: 181
		loop: 2
		cel: 7
		posn: 157 146
		setCycle: Beg (if argc param1 else 0)
	)
)

(procedure (localproc_021f)
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
		(else (theMusic1 number: 101 loop: -1 play: 75))
	)
)

(procedure (localproc_0287)
	(cond 
		((or (Btst 84) (>= (eureka puke?) 4))
			(if (!= (theMusic2 number?) 105)
				(theMusic2 number: 105 loop: -1 play:)
			)
		)
		((!= (theMusic2 number?) 206) (theMusic2 number: 206 loop: -1 play:))
	)
)

(instance theMusic4 of Sound
	(properties)
)

(instance rm200 of Rm
	(properties
		noun 16
		picture 40
		style $000a
		vanishingY 50
	)
	
	(method (init)
		(curRoom setRegions: 210)
		(LoadMany 128 181 0 2 183 184 185 186 180)
		(LoadMany 132 19)
		(walkHandler addToFront: chair northDoor)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 185 137 181 149 135 148 128 137
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						123
						141
						135
						152
						181
						152
						194
						142
						279
						142
						286
						141
						287
						139
						278
						134
						178
						133
						173
						123
						140
						123
						130
						134
						42
						135
						33
						141
					yourself:
				)
		)
		(curRoom setScript: sInitRoom)
		(super init:)
		(if (Btst 0)
			(sCyclePal init:)
			(theDoits add: sCyclePal)
		)
	)
	
	(method (dispose)
		(PalVary pvUNINIT)
		(walkHandler delete: chair northDoor)
		(if (Btst 0) (theDoits delete: sCyclePal))
		(super dispose:)
	)
)

(instance sInitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(leftDome init: setOnMeCheck: 1 2)
				(rightDome init: setOnMeCheck: 1 32)
				(leftConsole init:)
				(rightConsole init:)
				(roofPipes init: setOnMeCheck: 1 8)
				(navPanel init:)
				(comPanel init:)
				(auxStorage init: setOnMeCheck: 1 4)
				(chair init: setOnMeCheck: 1 1024)
				(northDoor init: stopUpd:)
				(if (OneOf prevRoomNum 100 201)
					(localproc_01cc)
					(ego init:)
				else
					(NormalEgo 0 2)
					(ego init: posn: 161 122 setScale: Scaler 110 70 152 111)
				)
				(switch prevRoomNum
					(165
						(ego setPri: 3)
						(= next sNewCaptain)
						(= eurekaCurLocation 0)
						(= global130 1)
						(droole init: loop: 2 cel: 1 setCycle: Fwd self)
						(floHead1 init:)
						(flo
							init:
							view: 184
							setLoop: 1
							setCycle: End self
							setScript: (sRandomScr new:)
						)
					)
					(201
						(cond 
							((Btst 85) (= next sAbandonShip))
							((and (Btst 108) (== eurekaCurLocation 5))
								(if (Btst 86)
									(= next sStandUp)
									(Bclr 108)
								else
									(= next sDrooleAtClorox2)
								)
							)
							((and (== (eureka puke?) 4) (not (Btst 86))) (= next sAbandonShip))
							((== (eureka puke?) 2) (= next sBlobKills))
							((Btst 106) (= next sKUComment))
							(else (= next sStandUp))
						)
						(if (not (Btst 86))
							(flo init:)
							(localproc_00d4)
							(if (not (Btst 97)) (droole init:) (localproc_0112))
						)
					)
					(225
						(= next sEnterNorth)
						(if (not (Btst 86))
							(flo init: setScript: sFloGoofOff)
							(if (not (Btst 97))
								(droole init: setScript: sDrooleGoofOff)
							)
						)
					)
					(else 
						(Bset 85)
						(= next sAbandonShip)
						(flo init:)
						(localproc_00d4)
						(droole init:)
						(localproc_0112)
					)
				)
				(= cycles 1)
			)
			(1
				(localproc_021f)
				(localproc_0287)
				(self dispose:)
			)
		)
	)
)

(instance sBlobKills of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register 3)
				(= seconds 1)
			)
			(1
				(ShakeScreen 3 3)
				(= cycles 1)
			)
			(2
				(if (-- register) (= state (- state 2)))
				(= cycles 1)
			)
			(3 (EgoDead 7) (self dispose:))
		)
	)
)

(instance sKUComment of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
				(Bclr 106)
			)
			(1 (localproc_01f0 self))
			(2
				(NormalEgo 0 2)
				(ego setScale: Scaler 110 70 152 111 x: 159 y: 147)
				(= seconds 3)
			)
			(3 (messager say: 9 0 0 1 self))
			(4 (messager say: 9 0 0 2 self))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFloAbandonShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(floHead2 dispose:)
				(flo
					view: 350
					setLoop: 0
					cel: 0
					x: 271 167
					setCycle: End self
					signal: (| $0004 (flo signal?))
				)
			)
			(1
				(flo
					setLoop: 2
					posn: 308 150
					setPri: -1
					setStep: 6
					cycleSpeed: 6
					setCycle: Walk
					setScale: Scaler 107 68 149 121
					setMotion: MoveTo 171 131 self
				)
			)
			(2
				(if (Btst 97) (northDoor setCycle: End))
				(= cycles 1)
			)
			(3
				(northDoor stopUpd:)
				(flo
					view: 189
					setPri: 2
					setLoop: 3
					posn: 165 132
					setCycle: Osc
					ignoreActors: 1
					setMotion: MoveTo 161 121 self
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance sDrooleAtClorox2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(droole
					view: 300
					setLoop: 0
					cel: 0
					posn: 28 170
					setCycle: End self
					signal: (| $0004 (droole signal?))
					setScript: 0
				)
			)
			(2
				(droole
					setLoop: 3
					setPri: -1
					posn: 0 151
					setSpeed: 6
					setStep: 7
					setScale: Scaler 119 60 150 117
					setCycle: Walk
					setMotion: MoveTo 125 139 self
				)
			)
			(3
				(northDoor setCycle: End self)
				(droole
					setLoop: 1
					cel: 0
					posn: 133 136
					setCycle: End self
				)
			)
			(4 0)
			(5
				(northDoor stopUpd:)
				(= cycles 4)
			)
			(6
				(messager say: 4 0 2 1 self)
				(Bset 97)
				(Bclr 108)
			)
			(7 (= cycles 3))
			(8 (droole setCycle: Beg self))
			(9
				(droole
					setLoop: 3
					cel: 4
					setStep: 7 3
					setScale: Scaler 119 60 150 117
					posn: 131 135
					setCycle: Walk
				)
				(= cycles 2)
			)
			(10
				(droole setMotion: MoveTo 153 127 self)
			)
			(11
				(droole setPri: 2 setMotion: MoveTo 155 117 self)
			)
			(12
				(northDoor setCycle: Beg self)
			)
			(13
				(northDoor stopUpd:)
				(droole dispose:)
				(= next sStandUp)
				(self dispose:)
			)
		)
	)
)

(instance sDrooleAbandonShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(droole
					view: 300
					setLoop: 0
					cel: 0
					posn: 28 170
					setCycle: End self
					signal: (| $0004 (droole signal?))
				)
			)
			(1
				(droole
					setLoop: 3
					setPri: -1
					posn: 0 151
					setSpeed: 6
					setStep: 7
					setScale: Scaler 119 60 150 117
					setCycle: Walk
					setMotion: MoveTo 125 139 self
				)
			)
			(2
				(droole
					setLoop: 1
					cel: 0
					posn: 133 136
					setCycle: End self
				)
			)
			(3 (= seconds 1))
			(4
				(cond 
					((and (< (eureka puke?) 4) (Btst 84)) (messager say: 4 0 1 1 self))
					(
					(and (== (eureka curLocation?) 4) (== global127 3)) (messager say: 4 0 3 1 self))
					(else (messager say: 4 0 1 2 self))
				)
			)
			(5 (= seconds 1))
			(6
				(droole setCycle: Beg self)
				(northDoor setCycle: End self)
			)
			(7 0)
			(8
				(northDoor stopUpd:)
				(droole
					setLoop: 3
					cel: 4
					setStep: 7 3
					setScale: Scaler 119 60 150 117
					posn: 131 135
					setCycle: Walk
					setMotion: MoveTo 153 127 self
				)
			)
			(9
				(droole setPri: 2 setMotion: MoveTo 155 117 self)
			)
			(10 (self dispose:))
		)
	)
)

(instance sAbandonShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(flo setScript: sFloAbandonShip self)
				(if (not (Btst 97))
					(droole setScript: sDrooleAbandonShip self)
				else
					(= cycles 1)
				)
			)
			(1 0)
			(2
				(northDoor setCycle: Beg self)
			)
			(3
				(northDoor stopUpd:)
				(flo dispose:)
				(droole dispose:)
				(= seconds 1)
			)
			(4
				(Bclr 85)
				(Bset 86)
				(if (Btst 84)
					(if (Btst 61) (= global136 180))
					(curRoom newRoom: 201)
				else
					(= next sStandUp)
				)
				(self dispose:)
			)
		)
	)
)

(instance sAttacked of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 4)
			)
			(1
				(ShakeScreen 3 3)
				(localproc_0156)
				(localproc_0135)
				(localproc_0179)
			)
			(2
				(localproc_01cc)
				(localproc_0112)
				(localproc_00d4)
				(= cycles 1)
			)
			(3 (localproc_01f0 self))
			(4
				(NormalEgo 0 2)
				(ego setScale: Scaler 110 70 152 111 x: 159 y: 147)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego priority: -1 setMotion: MoveFwd 10 self)
			)
			(1
				(northDoor setCycle: Beg self)
			)
			(2
				(northDoor stopUpd:)
				(if (!= prevRoomNum 165) (theGame handsOn:))
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
				(= cycles 1)
			)
			(1
				(northDoor setCycle: End self)
			)
			(2
				(ego setMotion: MoveTo 160 122 self)
			)
			(3
				(cond 
					((or (Btst 86) (== global130 0)) (= global130 0))
					((== wd40State 1) (= global130 4))
					((== global126 1) (= global130 1))
					((Btst 61) (= global130
						(switch (Random 0 1)
							(0 1)
							(1 2)
						)))
					(
						(or
							(== eurekaCurLocation 8)
							(== eurekaCurLocation 14)
							(== eurekaCurLocation 3)
						)
						(= global130 2)
					)
					(else (= global130 (Random 1 3)))
				)
				(if (== (theMusic2 number?) 206) (theMusic2 fade:))
				(curRoom newRoom: 225)
				(self dispose:)
			)
		)
	)
)

(instance sCyclePal of Script
	(properties)
	
	(method (doit)
		(if (== (PalVary pvGET_CURRENT_STEP) 64) (self cue:))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(PalVary pvUNINIT)
				(PalVary pvINIT 401 1)
			)
			(1
				(PalVary pvUNINIT)
				(PalVary pvINIT 403 1)
			)
			(2
				(PalVary pvUNINIT)
				(= state -1)
			)
		)
	)
	
	(method (cue)
		(self changeState: (+ state 1) &rest)
	)
)

(instance sDrooleGoofOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local8 (Random 0 3))
				(droole loop: [local0 local8] cel: 0 setCycle: Osc self)
				(if (== local8 3)
					(drooleArm init: setCycle: End drooleArm)
				)
				(= cycles 1)
			)
			(1
				(if (== local8 1)
					(theMusic3 number: 209 setLoop: -1 play:)
				)
				(= ticks (Random 120 220))
			)
			(2
				(if (== local8 3)
					(drooleArm setCycle: Beg self)
				else
					(= cycles 1)
				)
			)
			(3
				(switch local8
					(1 (theMusic3 stop:))
					(3 (drooleArm dispose:))
				)
				(droole setLoop: [local4 local8] setCycle: End self)
			)
			(4
				(localproc_0112)
				(self dispose:)
			)
		)
	)
)

(instance sFloGoofOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (= local9 (Random 0 1))
					(0
						(floHead1 init:)
						(flo view: 184 setLoop: 1 setCycle: Fwd self)
					)
					(1
						(flo view: 184 setLoop: 4 cel: 0 setCycle: Fwd self)
						(floHead1
							init:
							setLoop: 6
							cycleSpeed: 15
							setPri: 14
							posn: 260 117
							setCycle: Osc self
						)
					)
				)
				(= cycles 1)
			)
			(1 (= seconds (Random 2 3)))
			(2
				(if (== local9 1)
					(self changeState: 10)
				else
					(self changeState: (Random 3 5))
				)
			)
			(3
				(if (not (cast contains: floHead1)) (floHead1 init:))
				(flo view: 184 setLoop: 1 setCycle: Fwd)
				(self changeState: 8)
			)
			(4
				(if (cast contains: floHead1) (floHead1 dispose:))
				(flo setLoop: 2 setCycle: End)
				(self changeState: 8)
			)
			(5
				(if (cast contains: floHead1) (floHead1 dispose:))
				(flo setLoop: 3 setCycle: End self)
			)
			(6 (= ticks (Random 20 90)))
			(7
				(flo setCycle: Beg self)
				(self changeState: 11)
			)
			(8 (= ticks (Random 80 120)))
			(9
				(self changeState: (Random 3 5))
			)
			(10
				(floHead1 dispose:)
				(flo setLoop: 5 setCycle: End self)
			)
			(11
				(localproc_00d4)
				(self dispose:)
			)
		)
	)
)

(instance sRandomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 50 120)))
			(1 (client setCycle: End self))
			(2 (= ticks (Random 50 120)))
			(3 (client setCycle: Beg self))
			(4 (self changeState: 0))
		)
	)
)

(instance theMusic3 of Sound
	(properties)
)

(instance sNewCaptain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
				(theMusic3 number: 209 setLoop: -1 play: 80)
			)
			(1
				(theMusic4 number: 205 setLoop: 1 play: self)
			)
			(2
				(northDoor setCycle: End self)
			)
			(3
				(northDoor stopUpd:)
				(ego setPri: -1)
				(theMusic4 dispose:)
				(theMusic3 stop:)
				(droole loop: 3 cel: 0 setCycle: CT 1 1 self)
				(floHead1 dispose:)
				(flo loop: 3 cel: 0 setCycle: End self setScript: 0)
			)
			(4 0)
			(5
				(self setScript: sEnterNorth self)
			)
			(6
				(theGame handsOff:)
				(= seconds 2)
			)
			(7
				((ScriptID 1880 19) disposeWhenDone: 0)
				(messager say: 8 0 0 0 self)
			)
			(8
				(flo setCycle: Beg)
				(droole setCycle: CT 4 1)
				((ScriptID 1880 19) disposeWhenDone: 1)
				(ego setMotion: MoveFwd 2 self)
			)
			(9
				(ego
					view: 181
					setLoop: 0
					cel: 0
					x: 164
					y: 157
					setPri: 9
					setCycle: CT 2 1 self
				)
			)
			(10
				(ego setPri: -1 setCycle: CT 7 1 self)
				(theMusic3 number: 102 setLoop: 1 play:)
			)
			(11
				(theMusic3 number: 136 setLoop: 1 play:)
				(ego cel: 7 setCycle: CT 11 1 self)
				(droole loop: 3 cel: 0 setCycle: CT 1 1 self)
				(flo loop: 3 cel: 0 setCycle: End self setScript: 0)
			)
			(12 0)
			(13 0)
			(14
				(ego setCel: 11)
				(= seconds 3)
			)
			(15
				(ego posn: 157 149 setLoop: 1 cel: 0 setCycle: End self)
				(flo setCycle: Beg self)
				(droole setCycle: CT 4 1 self)
			)
			(16 0)
			(17 0)
			(18
				(NormalEgo 0 2)
				(localproc_00d4)
				(localproc_0112)
				(= cycles 1)
			)
			(19 (= seconds 2))
			(20
				(localproc_019e self)
				(= register 1)
			)
			(21
				(theMusic3 number: 213 loop: 1 play: self)
			)
			(22
				(messager say: 7 0 0 0 self)
			)
			(23 (= seconds 2))
			(24
				(ego
					setPri: 12
					setLoop: 3
					cel: 0
					x: 138
					y: 100
					setScale: 0
					setCycle: End self
				)
				(theMusic3 number: 431 loop: 1 play:)
			)
			(25
				(localproc_01cc)
				(= cycles 1)
			)
			(26
				(if register
					(self changeState: (- state 2))
					(= register 0)
				else
					(= cycles 1)
				)
			)
			(27 (= seconds 2))
			(28
				(= next sStandUp)
				(theMusic3 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sTakeCommand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1 (localproc_019e self))
			(2
				(theMusic3 number: 213 loop: 1 play: self)
			)
			(3
				(curRoom newRoom: 201)
				(theMusic3 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sStandUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (!= prevRoomNum 165)
					(theMusic3 number: 2471 setLoop: 1 play:)
				)
				(localproc_01f0 self)
			)
			(2
				(if (!= prevRoomNum 165)
					(theMusic3 number: 247 setLoop: 1 play: self)
				else
					(= cycles 1)
				)
				(NormalEgo 0 2)
				(ego setScale: Scaler 110 70 152 111 x: 159 y: 147)
			)
			(3
				(theGame handsOn:)
				(theMusic3 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance droole of Actor
	(properties
		x 40
		y 169
		noun 4
		view 183
		cycleSpeed 15
	)
)

(instance flo of Actor
	(properties
		x 268
		y 158
		noun 5
		view 184
		loop 1
		priority 12
		signal $4010
		cycleSpeed 10
	)
)

(instance floHead1 of Prop
	(properties
		x 264
		y 130
		noun 5
		view 184
		priority 12
		signal $4010
	)
)

(instance floHead2 of Prop
	(properties
		x 264
		y 130
		noun 5
		view 185
		priority 12
		signal $4010
	)
)

(instance drooleArm of Prop
	(properties
		x 39
		y 171
		noun 4
		view 183
		loop 5
		priority 12
		signal $4010
		cycleSpeed 5
	)
	
	(method (init)
		(paddleBall init:)
		(super init: &rest)
	)
	
	(method (doit)
		(switch cel
			(0
				(paddleBall
					x: 36
					y: 125
					scaleX: 33
					scaleY: 33
					maxScale: 33
				)
			)
			(1
				(paddleBall
					x: 44
					y: 129
					scaleX: 128
					scaleY: 128
					maxScale: 128
				)
			)
			(2
				(paddleBall
					x: 59
					y: 134
					scaleX: 194
					scaleY: 194
					maxScale: 194
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(paddleBall dispose:)
		(theMusic3 dispose:)
		(super dispose: &rest)
	)
	
	(method (cue)
		(if cel
			(self setCycle: Beg self)
			(theMusic3 number: 208 setLoop: 1 play:)
		else
			(self setCycle: End self)
		)
	)
)

(instance paddleBall of Prop
	(properties
		x 36
		y 125
		view 183
		loop 8
		priority 13
		signal $4010
		scaleSignal $0001
	)
)

(instance leftConsole of Feature
	(properties
		x 21
		y 100
		noun 10
		nsTop 85
		nsBottom 116
		nsRight 42
		sightAngle 40
	)
)

(instance navPanel of Feature
	(properties
		x 100
		y 185
		noun 12
		nsTop 153
		nsLeft 1
		nsBottom 199
		nsRight 77
		sightAngle 40
	)
)

(instance comPanel of Feature
	(properties
		x 225
		y 185
		noun 3
		nsTop 158
		nsLeft 240
		nsBottom 199
		nsRight 358
		sightAngle 40
	)
)

(instance roofPipes of Feature
	(properties
		x 144
		y 37
		noun 13
		sightAngle 40
		onMeCheck $0008
	)
)

(instance rightConsole of Feature
	(properties
		x 265
		y 185
		noun 14
		nsTop 85
		nsLeft 276
		nsBottom 123
		nsRight 319
		sightAngle 40
	)
)

(instance leftDome of Feature
	(properties
		x 45
		y 148
		noun 11
		sightAngle 40
		onMeCheck $0002
	)
)

(instance auxStorage of Feature
	(properties
		x 175
		y 175
		noun 1
		sightAngle 40
		onMeCheck $0004
	)
)

(instance rightDome of Feature
	(properties
		x 265
		y 120
		noun 15
		sightAngle 40
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 163
		y 175
		z 50
		noun 2
		sightAngle 30
		onMeCheck $0400
		approachX 159
		approachY 147
		approachDist 10
	)
	
	(method (init)
		(chair approachVerbs: 4 1 3)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sTakeCommand)
			)
			(3)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doorSound of Sound
	(properties)
)

(instance northDoor of Prop
	(properties
		x 157
		y 102
		noun 6
		approachX 160
		approachY 128
		view 180
	)
	
	(method (init)
		(super init: &rest)
		(self
			ignoreActors:
			approachVerbs: 4 3
			cel: (if (OneOf prevRoomNum 225) (self lastCel:) else 0)
		)
	)
	
	(method (dispose)
		(doorSound dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (self setScript: sExitNorth))
			(4 (self setScript: sExitNorth))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setCycle cType)
		(if cType (doorSound number: 103 loop: 1 play:))
		(super setCycle: cType &rest)
	)
)
