;;; Sierra Script 1.0 - (do not remove this comment)
(script# 199)
(include game.sh)
(use Main)
(use Intrface)
(use tahiti)
(use InitAllFeatures)
(use RFeature)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	danceRm 0
)

(local
	agent
	egoMover
)
(instance danceRm of Room
	(properties
		picture 199
	)
	
	(method (init)
		(super init:)
		(Load VIEW 199)
		(Load VIEW 699)
		(Load VIEW 499)
		(Load VIEW 299)
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
		((= agent (ScriptID 309 0))
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
		(husbandPV cel: (& (tahiti flags?) $0001))
		(if (& (tahiti flags?) $0002)
			(barPerson4PV cel: 3 x: 161 y: 38)
		else
			(barPerson4PV cel: 2)
		)
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
		(InitAllFeatures)
		(self setRegions: 300 setScript: barDanceS)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== (ego onControl: origin) cLRED)
				(!= (self script?) quitScript)
			)
			(self setScript: quitScript)
		)
	)
)

(instance barDanceS of Script
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (== (barDanceSong prevSignal?) -1))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(barDanceSong number: (SoundFX 7) play:)
				(ego setScript: danceScript)
			)
			(1
				(armP setCycle: 0)
				(footP setCycle: 0)
				(armGP setCycle: 0)
				(armKBP setCycle: 0)
			)
		)
	)
)

(instance agentDanceMoveTo of MoveTo
	
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
	
	(method (doit)
		(ego view: (if (ego mover?) 199 else 699))
	)
)

(instance agentDanceViewer of Code

	(method (doit)
		(agent view: (if (agent mover?) 299 else 499))
	)
)

(instance danceMoveTo of MoveTo
	
	(method (doit)
		(super doit:)
		(if (self onTarget:)
			(self motionCue:)
		)
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
				(HandsOn)
				(agent
					setCycle: Forward
					observeControl: (| cWHITE cYELLOW)
					setScript: agentsDanceS
				)
				(ego
					setCycle: Forward
					moveSpeed: 1
					observeControl: cYELLOW
				)
				(= cycles 2)
			)
			(1
				(= start state)
				(if
					(or
						(== (ego mover?) egoMover)
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
					(= egoMover (ego mover?))
					(= register (GetTime SYSTIME1))
					(agent cue:)
				)
				(= cycles (Random 10 40))
			)
			(2
				(if
					(and
						(== (barDanceSong prevSignal?) -1)
						(not demoScripts)
					)
					(client setScript: quitScript)
				else
					(self init:)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'exit,sit,cease,quit[/dancing]')
					(Said 'exit[/floor<dance]')
					(Said 'dancing<cease')
				)
				(barDanceSong fade:)
				(self setScript: quitScript)
			)
			(
				(or
					(Said 'buy,order/drink,(tai<mai)[/babe,stacy]')
					(Said 'buy,order/drink,(tai<mai)<babe,stacy')
				)
				(Print 199 0)
			)
			((Said 'hold,hug,kiss[/babe,stacy]')
				(Print 199 1)
			)
			((Said 'dance[/babe,stacy]')
				(Print 199 2)
			)
			((Said 'look<around')
				(Print 199 3)
			)
			((Said 'ask/babe/date')
				(Print 199 4)
			)
		)
	)
)

(instance quitScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(barDanceSong fade:)
				(ego setMotion: 0)
				(= cycles 15)
			)
			(1
				(ego ignoreControl: cYELLOW moveSpeed: 0)
				(curRoom newRoom: 11)
			)
		)
	)
)

(instance agentsDanceS of Script
	
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

(instance barPerson1PV of RPicView
	(properties
		y 47
		x 93
		view 399
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/!*]')
				(Print 199 5)
			)
		)
	)
)

(instance barPerson2PV of RPicView
	(properties
		y 59
		x 309
		view 399
		loop 1
		priority 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/!*]')
				(Print 199 6)
			)
		)
	)
)

(instance barPerson3PV of RPicView
	(properties
		y 46
		x 124
		view 399
		cel 1
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/!*]')
				(Print 199 7)
			)
		)
	)
)

(instance barPerson4PV of RPicView
	(properties
		y 45
		x 162
		view 399
		cel 2
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/!*]')
				(if (== cel 2)
					(Print 199 8)
				else
					(Print 199 9)
				)
			)
		)
	)
)

(instance barPerson5PV of RPicView
	(properties
		y 38
		x 195
		view 399
		cel 3
		priority 0
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/!*]')
				(Print 199 9)
			)
		)
	)
)

(instance barPerson6PV of RPicView
	(properties
		y 46
		x 255
		view 399
		loop 2
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/!*]')
				(Print 199 6)
			)
		)
	)
)

(instance keyBoardPV of RPicView
	(properties
		y 143
		x 17
		view 99
		loop 2
		priority 14
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/keyboard]')
				(Print 199 10)
			)
			((Said 'look[<at]/band')
				(Print 199 11)
			)
			((Said 'address/band')
				(switch (Random 0 3)
					(0 (Print 199 12))
					(1 (Print 199 13))
					(2 (Print 199 14))
					(3 (Print 199 15))
				)
			)
		)
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

(instance keyboardistPV of RPicView
	(properties
		y 161
		x 4
		view 99
		priority 14
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man]')
				(Print 199 16)
			)
			((Said 'address[/man]')
				(Print 199 17)
			)
		)
	)
)

(instance armGP of Prop
	(properties
		y 82
		x 15
		view 99
		loop 7
		priority 14
		signal fixPriOn
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self isExtra:)
	)
)

(instance guitaristPV of RPicView
	(properties
		y 118
		x 12
		view 99
		loop 6
		priority 14
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man,player<guitar]')
				(Print 199 18)
			)
			((Said 'address[/man]')
				(Print 199 19)
			)
		)
	)
)

(instance manInChairPV of RPicView
	(properties
		y 198
		x 215
		view 599
		loop 3
		priority 15
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man]')
				(Print 199 20)
			)
		)
	)
)

(instance ladyInChairPV of RPicView
	(properties
		y 198
		x 289
		view 599
		loop 2
		priority 15
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/babe[<blond]]')
				(Print 199 21)
			)
		)
	)
)

(instance flowersPV of RPicView
	(properties
		y 164
		x 248
		view 599
		loop 1
		priority 15
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/flower,centerpiece]')
				(Print 199 22)
			)
		)
	)
)

(instance tablePV of RPicView
	(properties
		y 189
		x 252
		view 599
		priority 14
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/table]')
				(Print 199 23)
			)
		)
	)
)

(instance guitarist2PV of RPicView
	(properties
		y 135
		x 42
		view 99
		loop 3
		priority 14
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man]')
				(Print 199 24)
			)
		)
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

(instance barDanceSong of Sound
	(properties
		number 7
		loop -1
	)
	
	(method (play)
		(= prevSignal 0)
		(armP setCycle: Forward)
		(footP setCycle: Forward)
		(armGP setCycle: Forward)
		(armKBP setCycle: Forward)
		(super play: &rest)
	)
)

(instance husbandPV of RPicView
	(properties
		y 81
		x 300
		view 599
		loop 4
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man]')
				(Print 199 25)
			)
		)
	)
)

(instance topTablePV of RPicView
	(properties
		y 79
		x 314
		view 599
		loop 5
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/table]')
				(if (husbandPV cel?)
					(Print 199 26)
				else
					(Print 199 27)
				)
			)
		)
	)
)
