;;; Sierra Script 1.0 - (do not remove this comment)
(script# 790)
(include sci.sh)
(use Main)
(use VelocityMover)
(use genetix)
(use Scaler)
(use PolyPath)
(use Polygon)
(use MoveFwd)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm790 0
	sUseComm 20
)

(local
	local0
	theGPEventX
	theGPEventY
)
(procedure (localproc_00f0)
	(ego moveSpeed: 5 cycleSpeed: 7 setStep: 3 3)
)

(instance theMusic3 of Sound
	(properties)
)

(instance theMusic4 of Sound
	(properties)
)

(instance rm790 of Rm
	(properties
		noun 13
		picture 116
	)
	
	(method (init)
		(LoadMany 143 number)
		(Bset 64)
		(self setRegions: 31)
		(if (not (Btst 22))
			(LoadMany 128 618 619 620 621 518 520)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							218
							46
							197
							47
							188
							51
							164
							69
							120
							105
							69
							115
							57
							127
							52
							129
							53
							158
							190
							157
							215
							147
							210
							144
							182
							145
							148
							122
							123
							124
							105
							131
							65
							130
							86
							120
							116
							114
							119
							112
							200
							56
							214
							51
							254
							51
							255
							46
						yourself:
					)
			)
		)
		(super init:)
		(Bclr 121)
		(computerFlashing init:)
		(if gRegister
			(computerFlashing init: setLoop: 9)
		else
			(computerFlashing init: setCycle: Fwd)
		)
		(botCageF init:)
		(otherCageF init:)
		(topCageButF init:)
		(creatureM init: setCycle: Fwd)
		(creatureL init: setScript: sWoscillate)
		(creatureR init: setCycle: Fwd)
		(steamPuff init: hide: setScript: sSteamPuff)
		(crFeatureM init:)
		(crFeatureR init:)
		(crFeatureL init:)
		(bigForceField init: setCycle: Fwd)
		(theGame handsOn:)
		(theMusic3 number: 101 flags: 1 loop: -1 play:)
		(if (Btst 22)
			(theMusic2 number: 600 loop: -1 play:)
			(theMusic2 setVol: (Min 127 (Max 30 (- (ego y?) 32))))
			(Door1 init: stopUpd:)
			(walkHandler addToFront: self)
			(walkHandler addToFront: computerFlashing)
			(if (== prevRoomNum 770)
				(theMusic1 number: 39 setLoop: -1 play:)
				(NormalFlyEgo 224 24)
			else
				(theIconBar enable: 7)
				(NormalFlyEgo 224 124)
			)
			(walkHandler addToFront: doorLock)
			(doorLock init:)
		else
			(if (Btst 83)
				(fog init: setLoop: 5 setCycle: Fwd)
				(nitroDoor init: setLoop: 2 cel: 2 addToPic:)
				(if (not (Btst 125)) (nitro init:))
			else
				(nitroDoorBut init:)
			)
			(topCageBut init:)
			(bottomCageBut init:)
			(walkHandler addToFront: self)
			(walkHandler addToFront: Door1)
			(switch prevRoomNum
				(730
					(curRoom setScript: sWalkIn)
				)
				(750
					(curRoom setScript: sBackFromComputer)
				)
				(else 
					(curRoom setScript: sWalkIn)
				)
			)
		)
	)
	
	(method (doit)
		(if (Btst 22)
			(ego setLoop: (/ (+ (ego heading?) 90) 180))
			(theMusic2 setVol: (Min 127 (Max 30 (- (ego y?) 32))))
		)
		(if (not (if script else (Btst 22)))
			(switch (ego onControl: 1)
				(64
					(curRoom setScript: sUpStairs)
				)
				(32
					(curRoom setScript: sDownStairs)
				)
				(1024
					(if (> (ego y?) 100) (curRoom setScript: sToComputer))
				)
				(16384
					(curRoom setScript: sUpSmallStairs)
				)
				(128
					(curRoom setScript: sDownSmallStairs)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(walkHandler delete: computerFlashing)
		(walkHandler delete: doorLock)
		(walkHandler delete: Door1)
		(DisposeScript 29)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (Btst 22)
					(proc31_2 mouseY)
					(ego setMotion: VelocityMover mouseX mouseY self 0)
				else
					(= theGPEventX mouseX)
					(= theGPEventY mouseY)
					(ego setMotion: PolyPath mouseX mouseY)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sToComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 518
					setLoop: 0
					cycleSpeed: 12
					setStep: 1 1
					setMotion: PolyPath 230 160 self
				)
			)
			(1
				(NormalEgo 0)
				(ego setMotion: MoveTo 253 160 self)
			)
			(2
				(ego setHeading: 0)
				(= seconds 2)
			)
			(3
				(theGame handsOn:)
				(curRoom newRoom: 750)
			)
		)
	)
)

(instance sBackFromComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Door1 init: stopUpd:)
				(NormalEgo 0)
				(ego
					init:
					ignoreActors:
					posn: 253 160
					setMotion: MoveTo 230 160 self
					setScale: Scaler 100 70 141 123
				)
			)
			(1
				(ego
					view: 520
					setLoop: 1
					cycleSpeed: 12
					setStep: 1 1
					setMotion: MoveTo 211 148 self
				)
			)
			(2
				(NormalEgo 0)
				(ego setMotion: MoveTo 187 150 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setCycle: 0)
				(if (ego looper?) ((ego looper?) dispose:))
				(= cycles 2)
			)
			(1
				(ego
					view: 520
					setLoop: 2
					setStep: 3 2
					moveSpeed: 3
					cycleSpeed: 6
					setCycle: Fwd
					setMotion: MoveTo 173 74 self
				)
			)
			(2
				(ego
					setScale: 0
					scaleX: 128
					scaleY: 128
					setMotion: MoveTo 215 51 self
				)
			)
			(3
				(NormalEgo 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpSmallStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_00f0)
				(ego
					setLoop: -1
					view: 520
					setLoop: 2
					setHeading: 45
					setMotion: MoveFwd 27 self
				)
			)
			(1
				(NormalEgo 0)
				(if (< theGPEventY 116)
					(ego setMotion: PolyPath theGPEventX theGPEventY)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDownStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_00f0)
				(ego
					setLoop: -1
					view: 518
					setLoop: 1
					setMotion: MoveTo 116 107 self
				)
				(= seconds 2)
			)
			(1
				(ego setScale: Scaler 100 70 141 123)
			)
			(2
				(NormalEgo 0)
				(if (> theGPEventY 110)
					(ego setMotion: PolyPath theGPEventX theGPEventY)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDownSmallStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: -1
					view: 518
					setLoop: 1
					setStep: 3 4
					setHeading: 235
					setMotion: MoveFwd 22 self
				)
			)
			(1
				(NormalEgo 0)
				(if (> theGPEventY 130)
					(ego setMotion: PolyPath theGPEventX theGPEventY)
				else
					(ego setMotion: MoveTo 70 138)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSteamPuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 8 20)))
			(1
				(steamPuff show: setCycle: End self)
			)
			(2
				(steamPuff hide:)
				(= cycles 1)
				(= state -1)
			)
		)
	)
)

(instance sFlyToComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc31_2 145)
				(ego setMotion: VelocityMover 245 145 self 1)
			)
			(1
				(theMusic2 stop:)
				(theGame handsOn:)
				(curRoom newRoom: 750)
			)
		)
	)
)

(instance sWoscillate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 4 15)))
			(1 (client setCycle: End self))
			(2 (= seconds (Random 4 15)))
			(3 (client setCycle: Beg self))
			(4 (= cycles 1) (= state -1))
		)
	)
)

(instance sWalkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Door1 init: setCycle: End self)
				(theMusic4 number: 103 loop: 1 play:)
			)
			(1
				(NormalEgo 0)
				(ego
					init:
					ignoreActors:
					posn: 274 50
					setScale: 0
					setMotion: MoveTo 224 50 self
					illegalBits: 0
				)
			)
			(2 (Door1 setCycle: Beg self))
			(3
				(Door1 stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTornado of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego y?) 130)
					(ego setMotion: PolyPath 173 141 self)
					(theGame handsOff:)
				else
					(messager say: 4 0 0 0)
					(self dispose:)
				)
			)
			(1
				(ego
					view: 619
					ignoreActors: 1
					setLoop: -1
					setCycle: 0
					posn: 173 141
					loop: 0
					init:
				)
				(= ticks 9)
			)
			(2 (ego setCycle: End self))
			(3 (= seconds 2))
			(4
				(topForceField init:)
				(= seconds 2)
			)
			(5
				(theMusic2 number: 616 loop: 1 play:)
				(ego setLoop: 1 cel: 0 setCycle: End self)
			)
			(6
				(ego
					setLoop: 5
					setCycle: Fwd
					setMotion: MoveTo 103 137 self
				)
			)
			(7 (= seconds 1))
			(8
				(tornado init: setMotion: MoveTo 2 127 setCycle: Fwd)
				(ego setLoop: 2 setCycle: End self)
			)
			(9 (ego setCycle: End self))
			(10 (ego setCycle: End self))
			(11 (ego setCycle: End self))
			(12 (ego setCycle: CT 4 1 self))
			(13
				(ego setLoop: 3 cel: 0 setCycle: CT 4 1 self)
			)
			(14
				(ego setCycle: End self)
				(ShakeScreen 1)
				(theMusic3 number: 136 loop: 1 play: self)
			)
			(15
				(EgoDead 27)
				(self dispose:)
			)
		)
	)
)

(instance sBlood of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego y?) 130)
					(ego setMotion: PolyPath 169 141 self)
					(theGame handsOff:)
				else
					(messager say: 4 0 0 0)
					(self dispose:)
				)
			)
			(1
				(ego
					scaleSignal: 0
					scaleX: 128
					scaleY: 128
					setCycle: 0
					view: 620
					setLoop: 0
					cel: 0
					setPri: 7
					cycleSpeed: 12
					ignoreActors: 1
				)
				(= ticks 9)
			)
			(2 (ego setCycle: End self))
			(3
				(bottomForceField init:)
				(= ticks 9)
			)
			(4
				(ego setLoop: 1)
				(= ticks 9)
			)
			(5
				(ego setCycle: End self)
				(theMusic2 number: 228 loop: 1 play:)
			)
			(6
				(ego setLoop: 2 setCycle: End self)
				(theMusic2 number: 228 loop: 1 play:)
			)
			(7
				(theMusic2 number: 228 loop: 1 play:)
				(ego setLoop: 2 setCycle: End self)
				(blood init: setCycle: End)
			)
			(8
				(ego setLoop: 2 setCycle: End self)
				(blood setCycle: End)
			)
			(9
				(ego setMotion: MoveTo 178 141 self)
			)
			(10
				(ego setLoop: 2 setCycle: End self)
				(blood setCycle: End)
			)
			(11
				(ego setLoop: 2 setCycle: End self)
				(blood setCycle: End)
			)
			(12
				(ego cel: 3 setMotion: MoveTo 206 141 self)
			)
			(13
				(ego dispose:)
				(blood setCycle: End self)
			)
			(14
				(blood dispose:)
				(= seconds 2)
			)
			(15
				(theMusic2 number: 502 loop: 1 play: self)
			)
			(16 (EgoDead 27))
		)
	)
)

(instance sOpenNitroDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 83)
				(ego setMotion: PolyPath 73 117 self)
			)
			(1
				(ego view: 621 setLoop: 0 cel: 0 setCycle: End self)
			)
			(2
				(nitroDoor init: setCycle: End self)
				(theMusic4 number: 108 setLoop: 1 play:)
			)
			(3
				(fog init: setCycle: End self)
			)
			(4
				(fog setLoop: 5 setCycle: Fwd)
				(NormalEgo 0)
				(ego setMotion: MoveTo 92 112 self)
			)
			(5
				(ego setHeading: 0)
				(nitro init: setPri: 1)
				(nitroDoor dispose:)
				(nitroDoorBut dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLeaveFly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: VelocityMover 238 25 self 1)
			)
			(1 (= seconds 2))
			(2
				(ego dispose:)
				(theGame handsOn:)
				(curRoom newRoom: 770)
			)
		)
	)
)

(instance Door1 of Prop
	(properties
		x 242
		y -10
		noun 2
		view 618
		signal $5000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (< (ego y?) 60)
					(Door1 startUpd: setCycle: End)
					(theMusic4 number: 103 loop: 1 play:)
					(curRoom setScript: (ScriptID 31 3) 0 9)
				else
					(ego setMotion: PolyPath 232 51)
				)
			)
			(4
				(if (< (ego y?) 60)
					(Door1 startUpd: setCycle: End)
					(curRoom setScript: (ScriptID 31 3) 0 9)
				else
					(messager say: 4 0 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance nitro of MyProp
	(properties
		x 63
		y 73
		noun 12
		onMeCheck $0800
		view 621
		loop 1
		cel 6
		priority 7
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (ego has: 16))
					(self loop: 2 cel: 2)
					(SolvePuzzle 234 50)
					(ego get: 16)
					(Bset 125)
					(Bset 102)
				)
			)
			(1
				(if (Btst 82)
					(messager say: 12 1 1)
				else
					(messager say: 12 1 2)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance creatureM of MyProp
	(properties
		x 68
		y 163
		noun 6
		nsTop 155
		nsLeft 58
		nsBottom 189
		nsRight 112
		view 618
		loop 1
		cel 2
		priority 14
		signal $4010
		cycleSpeed 60
	)
)

(instance creatureR of MyProp
	(properties
		x 296
		y 107
		noun 7
		nsTop 100
		nsLeft 286
		nsBottom 162
		nsRight 319
		view 618
		loop 2
		cel 1
		priority 14
		signal $4010
		cycleSpeed 20
	)
)

(instance creatureL of MyProp
	(properties
		x 2
		y 120
		noun 5
		nsTop 110
		nsBottom 149
		nsRight 32
		view 618
		loop 3
		cel 2
		priority 14
		signal $4010
	)
)

(instance crFeatureL of MyFeature
	(properties
		x 2
		y 120
		noun 5
		nsTop 110
		nsBottom 149
		nsRight 32
	)
)

(instance crFeatureM of MyFeature
	(properties
		x 68
		y 163
		noun 6
		nsTop 155
		nsLeft 58
		nsBottom 189
		nsRight 112
	)
)

(instance crFeatureR of MyFeature
	(properties
		x 296
		y 107
		noun 7
		nsTop 100
		nsLeft 286
		nsBottom 162
		nsRight 319
	)
)

(instance doorF of MyFeature
	(properties
		x 255
		y 22
		noun 2
		nsLeft 248
		nsBottom 45
		nsRight 265
	)
)

(instance topCageButF of MyFeature
	(properties
		x 192
		y 89
		noun 15
		nsTop 85
		nsLeft 181
		nsBottom 99
		nsRight 196
		onMeCheck $0100
	)
)

(instance botCageF of MyFeature
	(properties
		x 190
		y 110
		noun 1
		nsTop 103
		nsLeft 181
		nsBottom 119
		nsRight 197
		onMeCheck $0100
	)
)

(instance otherCageF of MyFeature
	(properties
		x 140
		y 111
		noun 3
		nsTop 89
		nsLeft 158
		nsBottom 114
		nsRight 180
		onMeCheck $0100
	)
)

(instance nitroDoorBut of MyFeature
	(properties
		x 57
		y 89
		onMeCheck $0004
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(if (& (= temp0 (ego onControl: 1)) $0010)
					(curRoom setScript: sOpenNitroDoor)
				else
					(messager say: 4 0 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance doorLock of MyFeature
	(properties
		x 238
		y 25
		noun 14
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: sLeaveFly)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance topCageBut of MyFeature
	(properties
		x 257
		y 100
		onMeCheck $1000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (> (ego y?) 70)
					(curRoom setScript: sTornado)
				else
					(messager say: 4 0 0 0)
				)
			)
			(3
				(if (< (ego y?) 60) (curRoom setScript: sTornado))
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bottomCageBut of MyFeature
	(properties
		x 257
		y 120
		onMeCheck $0200
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (curRoom setScript: sBlood))
			(3
				(curRoom setScript: sTornado)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance steamPuff of MyProp
	(properties
		x 113
		y -6
		view 618
		loop 5
	)
)

(instance computerFlashing of MyProp
	(properties
		x 243
		y 141
		noun 16
		onMeCheck $0002
		view 618
		loop 6
		cycleSpeed 20
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (not (curRoom script?))
					(curRoom setScript: sFlyToComputer)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance nitroDoor of MyActor
	(properties
		x 63
		y 73
		noun 8
		view 621
		loop 1
		cel 1
		signal $4000
	)
)

(instance bigForceField of MyActor
	(properties
		x 156
		y 83
		view 618
		loop 4
		cel 1
		signal $4000
	)
)

(instance tornado of MyActor
	(properties
		x 103
		y 137
		view 619
		loop 4
		signal $0800
	)
)

(instance blood of MyProp
	(properties
		x 187
		y 110
		view 620
		loop 3
		cel 6
		priority 15
		signal $0010
	)
)

(instance topForceField of View
	(properties
		x 179
		y 84
		view 618
		loop 7
		priority 6
		signal $0010
	)
)

(instance bottomForceField of View
	(properties
		x 184
		y 103
		view 618
		loop 8
	)
)

(instance fog of MyProp
	(properties
		x 67
		y 98
		noun 11
		view 621
		loop 4
	)
)

(instance sUseComm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 14 loop: 0 cel: 0 setCycle: End self)
				(theMusic2 number: 603 setLoop: 1 play:)
			)
			(1 (= seconds 2))
			(2
				(messager say: 17 0 0 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(NormalEgo 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
