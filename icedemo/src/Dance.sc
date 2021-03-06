;;; Sierra Script 1.0 - (do not remove this comment)
(script# DANCE)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	danceRm 0
)

(local
	agent
	gEgoMover
)
(instance danceRm of Room
	(properties
		picture 199
	)
	
	(method (init)
		(LoadMany VIEW 199 699 499 299)
		(LoadMany SCRIPT AVOIDER SIGHT)
		(Load SOUND (SoundFX 7))
		(super init:)
		(ego
			view: 699
			posn: 163 111
			xStep: 5
			yStep: 3
			setAvoider: Avoider
			viewer: egoDanceViewer
			loop: 3
			init:
		)
		(armKBP init:)
		(armGP init:)
		(armP init:)
		(footP init:)
		((= agent (Actor new:))
			posn: 173 95
			view: 299
			setCycle: Walk
			ignoreActors: 0
			xStep: 5
			yStep: 3
			viewer: agentDanceViewer
			loop: 2
			setAvoider: Avoider
			init:
		)
		(husbandPV cel: 1)
		(barPerson4PV cel: 2)
		(addToPics
			add:
				barPerson1PV
				barPerson2PV
				barPerson3PV
				barPerson4PV
				barPerson5PV
				barPerson6PV
				keyBoardPV
				keyboardistPV
				guitaristPV
				manInChairPV
				ladyInChairPV
				guitarist2PV
				flowersPV
				tablePV
				husbandPV
				topTablePV
			eachElementDo: #init
			doit:
		)
		(self setScript: barDanceS)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript AVOIDER)
		(DisposeScript SIGHT)
	)
)

(instance barDanceS of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (not state) (== (globalSound prevSignal?) -1))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound
					number: (SoundFX 7)
					loop: -1
					prevSignal: 0
					play:
				)
				(armP setCycle: Forward)
				(footP setCycle: Forward)
				(armGP setCycle: Forward)
				(armKBP setCycle: Forward)
				(ego observeControl: 16384 setScript: danceScript)
				(Print 199 0 #at -1 12 #dispose)
				(= seconds 8)
			)
			(1 (= seconds 8))
			(2
				(agent setCycle: 0 setMotion: 0 setAvoider: 0)
				(ego setCycle: 0 setMotion: 0 setAvoider: 0)
				(armP setCycle: 0)
				(footP setCycle: 0)
				(armGP setCycle: 0)
				(armKBP setCycle: 0)
				(= cycles 3)
			)
			(3 (curRoom newRoom: 18))
		)
	)
)

(instance agentDanceMoveTo of MoveTo
	(properties)
	
	(method (init param1 param2 param3)
		(agent
			heading: (GetAngle (agent x?) (agent y?) (ego x?) (ego y?))
			view: 299
		)
		(super init: param1 param2 param3)
	)
	
	(method (doit)
		(super doit:)
		(if (self onTarget:) (self motionCue:))
	)
	
	(method (dispose)
		(client
			heading: (GetAngle (agent x?) (agent y?) (ego x?) (ego y?))
			view: 499
		)
		(DirLoop client (client heading?))
		(super dispose:)
	)
	
	(method (onTarget)
		(return
			(if (< (Abs (- (client x?) x)) 7)
				(< (Abs (- (client y?) y)) 5)
			else
				0
			)
		)
	)
)

(instance egoDanceViewer of Code
	(properties)
	
	(method (doit)
		(ego view: (if (ego mover?) 199 else 699))
	)
)

(instance agentDanceViewer of Code
	(properties)
	
	(method (doit)
		(agent view: (if (agent mover?) 299 else 499))
	)
)

(instance danceMoveTo of MoveTo
	(properties)
	
	(method (doit)
		(super doit:)
		(if (self onTarget:) (self motionCue:))
	)
	
	(method (onTarget)
		(return
			(if (< (Abs (- (client x?) x)) 7)
				(< (Abs (- (client y?) y)) 5)
			else
				0
			)
		)
	)
)

(instance danceScript of Script
	(properties
		register 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(agent
					setCycle: Forward
					observeControl: cYELLOW
					setScript: agentsDanceS
				)
				(ego setCycle: Forward moveSpeed: 1 observeControl: cYELLOW)
				(= cycles 2)
			)
			(1
				(= start state)
				(if
					(or
						(== (ego mover?) gEgoMover)
						(>= (Abs (- register (GetTime SYSTIME1))) 10)
					)
					(if (Random 0 3)
						(ego heading: (Random 0 360))
						(if (ego looper?)
							((ego looper?) doit: ego (ego looper?))
						else
							(DirLoop ego (ego heading?))
						)
					else
						(ego
							setMotion: danceMoveTo (Random 130 180) (Random 105 135)
						)
					)
					(= gEgoMover (ego mover?))
					(= register (GetTime SYSTIME1))
					(agent cue:)
				)
				(= cycles (Random 10 40))
			)
			(2 (self init:))
		)
	)
)

(instance agentsDanceS of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
		(= register (ego mover?))
		(switch (= state newState)
			(0
				(if register
					(= egoX ((ego mover?) x?))
					(= egoY ((ego mover?) y?))
					(cond 
						((< egoX 110) (= egoX 110))
						((> egoX 220) (= egoX 220))
					)
					(cond 
						((< egoY 110) (= egoY 110))
						((> egoY 170) (= egoY 170))
					)
				else
					(= egoX (ego x?))
					(= egoY (ego y?))
				)
				(agent
					setMotion:
						agentDanceMoveTo
						(+ egoX (SinMult (ego heading?) 30))
						(- egoY (/ (* (CosMult (ego heading?) 30) 2) 3))
				)
				(= cycles 35)
			)
			(1 (self init:))
		)
	)
)

(instance barPerson1PV of PicView
	(properties
		y 47
		x 93
		view 399
		priority 1
	)
)

(instance barPerson2PV of PicView
	(properties
		y 59
		x 309
		view 399
		loop 1
		priority 2
	)
)

(instance barPerson3PV of PicView
	(properties
		y 46
		x 124
		view 399
		cel 1
		priority 1
	)
)

(instance barPerson4PV of PicView
	(properties
		y 45
		x 162
		view 399
		cel 2
		priority 1
	)
)

(instance barPerson5PV of PicView
	(properties
		y 38
		x 195
		view 399
		cel 3
		priority 0
	)
)

(instance barPerson6PV of PicView
	(properties
		y 46
		x 255
		view 399
		loop 2
		priority 1
	)
)

(instance keyBoardPV of PicView
	(properties
		y 143
		x 17
		view 99
		loop 2
		priority 14
	)
)

(instance armKBP of Prop
	(properties
		y 114
		x 7
		view 99
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 isExtra:)
	)
)

(instance keyboardistPV of PicView
	(properties
		y 161
		x 4
		view 99
		priority 14
	)
)

(instance armGP of Prop
	(properties
		y 82
		x 15
		view 99
		loop 7
		priority 14
		signal $0010
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self isExtra:)
	)
)

(instance guitaristPV of PicView
	(properties
		y 118
		x 12
		view 99
		loop 6
		priority 14
	)
)

(instance manInChairPV of PicView
	(properties
		y 198
		x 215
		view 599
		loop 3
		priority 15
	)
)

(instance ladyInChairPV of PicView
	(properties
		y 198
		x 289
		view 599
		loop 2
		priority 15
	)
)

(instance flowersPV of PicView
	(properties
		y 164
		x 248
		view 599
		loop 1
		priority 15
	)
)

(instance tablePV of PicView
	(properties
		y 189
		x 252
		view 599
		priority 14
	)
)

(instance guitarist2PV of PicView
	(properties
		y 135
		x 42
		view 99
		loop 3
		priority 14
	)
)

(instance armP of Prop
	(properties
		y 96
		x 48
		view 99
		loop 4
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 isExtra:)
	)
)

(instance footP of Prop
	(properties
		y 135
		x 47
		view 99
		loop 5
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 isExtra:)
	)
)

(instance husbandPV of PicView
	(properties
		y 81
		x 300
		view 599
		loop 4
		priority 4
	)
)

(instance topTablePV of PicView
	(properties
		y 79
		x 314
		view 599
		loop 5
		priority 4
	)
)
