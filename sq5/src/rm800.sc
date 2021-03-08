;;; Sierra Script 1.0 - (do not remove this comment)
(script# 800)
(include game.sh) (include "800.shm")
(use Main)
(use Feature)
(use ScaleTo)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm800 0
)

(local
	[local0 131] = [2 1 0 3 4 4 0 0 0 5 1 0 1 10 1 1 -1 8 1 1 -1 8 1 1 1 8 1 1 1 14 1 1 -1 9 1 1 -6 5 1 2 -7 0 1 3 -12 -1 1 3 -10 1 1 3 -12 1 1 3 -7 -1 1 3 -5 -6 0 0 0 -7 3 1 1 -5 3 1 -1 -9 3 1 1 -11 3 1 -1 -10 3 1 1 -5 3 1 1 -5 3 1 -1 -4 3 1 -1 -5 3 1 5 -5 2 2 9 0 2 3 9 1 2 3 10 -1 2 3 15 1 2 3 5 -1 2 3 2 1 2 3 -99 -99 2]
	[local131 40]
	local171
	local172
	local173
	local174 =  1
	local175 =  1
	local176
	local177
	local178
)
(procedure (localproc_0068 param1 param2 &tmp [temp0 2] temp2 temp3)
	(if (+ [local131 0] [local131 1])
		(Graph
			GDrawLine
			[local131 0]
			[local131 1]
			[local131 0]
			[local131 1]
			0
			8
			-1
		)
	)
	(= temp2 2)
	(while (< temp2 39)
		(= [local131 (- temp2 2)] [local131 temp2])
		(= [local131 (- temp2 1)] [local131 (+ temp2 1)])
		(if
		(+ [local131 (- temp2 2)] [local131 (- temp2 1)])
			(= temp3 (+ 8 (* (/ temp2 10) 2)))
			(Graph
				GDrawLine
				[local131 (- temp2 2)]
				[local131 (- temp2 1)]
				[local131 (- temp2 2)]
				[local131 (- temp2 1)]
				temp3
				8
				-1
			)
		)
		(= temp2 (+ temp2 2))
	)
	(= [local131 38] param2)
	(= [local131 39] param1)
)

(procedure (DrawLaserBeam &tmp laserBeamX laserBeamY laserBeamX_2 laserBeamY_2 temp4)
	(switch [local0 (+ (* local171 4) 3)]
		(0
			(= laserBeamX (- (LaserBeam x?) 3))
			(= laserBeamX_2 (+ (LaserBeam x?) 3))
			(= laserBeamY (+ (LaserBeam y?) 3))
			(= laserBeamY_2 (- (LaserBeam y?) 3))
		)
		(1
			(= laserBeamY (LaserBeam y?))
			(= laserBeamY_2 (LaserBeam y?))
			(= laserBeamX (- (LaserBeam x?) 3))
			(= laserBeamX_2 (+ (LaserBeam x?) 3))
		)
		(2
			(= laserBeamX (- (LaserBeam x?) 3))
			(= laserBeamX_2 (+ (LaserBeam x?) 3))
			(= laserBeamY (- (LaserBeam y?) 3))
			(= laserBeamY_2 (+ (LaserBeam y?) 3))
		)
		(3
			(= laserBeamX (LaserBeam x?))
			(= laserBeamX_2 (LaserBeam x?))
			(= laserBeamY (- (LaserBeam y?) 3))
			(= laserBeamY_2 (+ (LaserBeam y?) 3))
		)
	)
	(Graph
		GDrawLine
		laserBeamY
		laserBeamX
		laserBeamY_2
		laserBeamX_2
		-1
		8
		-1
	)
	(= laserBeamX (LaserBeam x?))
	(= laserBeamY (LaserBeam y?))
	(= laserBeamX_2 (LaserBeam x?))
	(= laserBeamY_2 (LaserBeam y?))
	(Graph
		GDrawLine
		laserBeamY
		laserBeamX
		laserBeamY_2
		laserBeamX_2
		-1
		8
		-1
	)
)

(procedure (localproc_024d)
	(= local173 [local0 (* local171 4)])
	(= local172 [local0 (+ (* local171 4) 1)])
	(= local175 (if (< local173 0) -1 else 1))
	(= local174 (if (< local172 0) -1 else 1))
	(= local173 (Abs local173))
	(= local172 (Abs local172))
	(return (== (++ local171) 32))
)

(procedure (localproc_0298)
	(return
		(if (and (not local172) (not local173))
			(return (localproc_024d))
		else
			(if local172
				(-- local172)
				(LaserBeam y: (+ (LaserBeam y?) local174))
			)
			(if local173
				(-- local173)
				(LaserBeam x: (+ (LaserBeam x?) local175))
			)
			(localproc_0068 (LaserBeam x?) (LaserBeam y?))
			(return 0)
		)
	)
)

(procedure (DontMove)
	(theGame handsOn:)
	(theIconBar disable: ICON_WALK ICON_TALK ICON_ORDER)
)

(instance rm800 of Room
	(properties
		noun N_ROOM
		picture 49
	)
	
	(method (init)
		(doorSwitch init: stopUpd:)
		(light init: stopUpd:)
		(super init: &rest)
		(Load RES_VIEW 699)
		(Load RES_PIC 49)
		(RingThatHideBurn init:)
		(InsideWallPiece init: stopUpd:)
		(tDoor init:)
		(bDoor init:)
		(goliathHullF init: setOnMeCheck: ftrDefault)
		(if (not (ego has: iCuttingTorch))
			(messager say: N_NO_TORCH NULL NULL 0)
			(EgoDead deathNOTORCH)
		else
			(DontMove)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TORCH
				(if (cast contains: tDoor)
					(messager say: noun V_TORCH C_CLOSED 0)
				else
					(curRoom setScript: cutTheHole)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cutTheHole of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local176 1)
				(SolvePuzzle f800CutHole 100)
				(localproc_024d)
				(GunInHand
					init:
					setStep: 10 10
					setMotion: MoveTo 129 76 self
				)
			)
			(1
				(theMusic2 number: 606 loop: -1 play:)
				(Load RES_PIC 49)
				(LaserBeam init:)
				(= local176 0)
				(= cycles 1)
			)
			(2
				(if (not (localproc_0298))
					(DrawLaserBeam)
					(-- state)
				)
				(= ticks 1)
			)
			(3
				(theMusic2 stop:)
				(= local176 1)
				(curRoom drawPic: (curRoom picture?) 100)
				(LaserBeam dispose:)
				(RingThatHideBurn dispose:)
				(GunInHand setMotion: MoveTo 60 86)
				(InsideWallPiece
					setScale: ScaleTo 125
					setStep: 10 10
					setMotion: MoveTo 159 82 self
				)
			)
			(4
				(InsideWallPiece setMotion: MoveTo 159 160 self)
			)
			(5
				(theMusic2 number: 517 loop: 1 play:)
				(GunInHand dispose:)
				(InsideWallPiece dispose:)
				(ego put: iCuttingTorch)
				(messager say: N_CUT_HOLE NULL NULL 0 self)
			)
			(6
				(if (not (Btst fInGoliathChamber))
					(theMusic1 number: 20 loop: -1 play:)
				)
				(= seconds 2)
			)
			(7
				(if (not (Btst fInGoliathChamber))
					(= next youDie)
					(self dispose:)
				else
					(SolvePuzzle f800InfiltratedGoliath 50)
					(curRoom newRoom: 1000)
				)
			)
		)
	)
)

(instance youDie of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(goon1 init: setMotion: MoveTo 142 109 self)
			)
			(2
				(goon2 init: setMotion: MoveTo 137 79 self)
			)
			(3
				(goon3 init: setMotion: MoveTo 180 71 self)
			)
			(4
				(theMusic1 fade: 0 10 5 self)
			)
			(5 (EgoDead deathWRONGSECTION))
		)
	)
)

(instance openDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(light setCycle: EndLoop)
				(doorSwitch setCycle: EndLoop)
				(theMusic2 number: 106 loop: 1 play:)
				(tDoor setMotion: MoveTo (tDoor x?) -50 self)
				(bDoor setMotion: MoveTo (bDoor x?) 153 self)
			)
			(1)
			(2
				(doorSwitch stopUpd:)
				(light stopUpd:)
				(tDoor dispose:)
				(bDoor dispose:)
				(DontMove)
				(self dispose:)
			)
		)
	)
)

(instance RingThatHideBurn of View
	(properties
		x 121
		y 48
		noun 2
		view 699
		loop 4
		priority 7
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb)
		(if (cast contains: tDoor)
			(tDoor doVerb: &rest)
		else
			(goliathHullF doVerb: &rest)
		)
	)
)

(instance LaserBeam of Actor
	(properties
		x 181
		y 50
		yStep 3
		view 699
		cel 1
		priority 13
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		moveSpeed 5
	)
	
	(method (init)
		(super init: &rest)
		(FireBall init: setCycle: Forward)
	)
	
	(method (dispose)
		(FireBall dispose:)
		(super dispose: &rest)
	)
)

(instance FireBall of Prop
	(properties
		x 190
		y 55
		view 699
		loop 2
		cel 1
		priority 15
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		cycleSpeed 4
	)
	
	(method (doit)
		(self x: (LaserBeam x?) y: (LaserBeam y?))
		(super doit: &rest)
	)
)

(instance InsideWallPiece of Actor
	(properties
		x 159
		y 85
		noun 2
		yStep 40
		view 699
		loop 6
		priority 6
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		xStep 10
		moveSpeed 0
	)
	
	(method (doVerb theVerb)
		(if (cast contains: tDoor)
			(tDoor doVerb: theVerb &rest)
		else
			(goliathHullF doVerb: theVerb &rest)
		)
	)
)

(instance GunInHand of Actor
	(properties
		x 61
		y 96
		yStep 10
		view 699
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		xStep 10
		moveSpeed 0
	)
	
	(method (doit)
		(if (not local176)
			(self
				x: (- (LaserBeam x?) 52)
				y: (+ (LaserBeam y?) 26)
			)
		)
		(super doit: &rest)
	)
)

(instance tDoor of Actor
	(properties
		x 98
		y 23
		noun 3
		yStep 5
		view 699
		loop 5
		cel 1
		priority 8
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		xStep 10
		moveSpeed 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(30
				(messager say: noun theVerb 2 0)
			)
			(4
				(messager say: noun theVerb 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bDoor of Actor
	(properties
		x 98
		y 92
		noun 3
		yStep 5
		view 699
		loop 5
		priority 8
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		xStep 10
		moveSpeed 0
	)
	
	(method (doVerb)
		(tDoor doVerb: &rest)
	)
)

(instance goon1 of Actor
	(properties
		x 117
		y 130
		view 699
		loop 1
		priority 6
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		moveSpeed 12
	)
)

(instance goon2 of Actor
	(properties
		x 115
		y 79
		view 699
		loop 1
		cel 1
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		moveSpeed 12
	)
)

(instance goon3 of Actor
	(properties
		x 203
		y 71
		view 699
		loop 1
		cel 2
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		moveSpeed 12
	)
)

(instance doorSwitch of Prop
	(properties
		x 234
		y 77
		noun 1
		view 699
		loop 7
		priority 15
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(30
				(messager say: noun theVerb 0 0)
			)
			(4
				(if (not local178)
					(= local178 1)
					(curRoom setScript: openDoor)
				else
					(messager say: noun theVerb 1 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance light of Prop
	(properties
		x 132
		y 7
		view 699
		loop 8
		priority 15
		signal $0010
	)
)

(instance goliathHullF of Feature
	(properties
		x 158
		y 2
		noun 2
		nsTop 42
		nsLeft 112
		nsBottom 143
		nsRight 204
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(30
				(if (cast contains: tDoor)
					(messager say: noun 30 2 0)
				else
					(curRoom setScript: cutTheHole)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
