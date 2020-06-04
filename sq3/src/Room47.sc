;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
(include game.sh)
(use Main)
(use Intrface)
(use Dune)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room47 0
)

(local
	local0
	egoY
	egoX
	local3
)
(instance tin of View
	(properties)
)

(instance rightArm of Prop
	(properties)
)

(instance leftArm of Prop
	(properties)
)

(instance mouth of Prop
	(properties)
)

(instance Room47 of Room
	(properties
		picture 47
	)
	
	(method (init)
		(= east 48)
		(= west 46)
		(= south 51)
		(= global109 13)
		(= global591 (| global109 (= local3 5)))
		(super init:)
		(= global116 0)
		(tin
			view: 72
			loop: 2
			cel: 0
			ignoreActors:
			posn: 190 66
			setPri: 0
			init:
			addToPic:
		)
		(rightArm
			view: 72
			ignoreActors:
			loop: 0
			cel: 8
			posn: 209 66
			setPri: 0
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		(leftArm
			view: 72
			ignoreActors:
			loop: 1
			cel: 0
			posn: 170 66
			setPri: 0
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		(mouth
			view: 72
			ignoreActors:
			loop: 3
			cel: 1
			posn: 190 56
			setPri: 1
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(if (not howFast)
			(leftArm addToPic:)
			(rightArm cel: 5 addToPic:)
			(mouth addToPic:)
		)
		(ego init:)
		(NormalEgo)
		(switch prevRoomNum
			(43
				(= global104 4)
				(ego posn: 192 72 view: 68 setPri: 2 setStep: 1 1)
			)
			(46
				(switch global104
					(4
						(ego posn: 1 90 view: 68 setStep: 1 1 setPri: 2)
					)
					(0 (ego posn: 1 (ego y?)))
					(3
						(ego posn: 1 85 looper: (ScriptID DUNE 1) setPri: 2)
					)
					(1
						(ego
							posn: 1 (ego y?)
							looper: (ScriptID DUNE 1)
							setPri: 4
						)
					)
					(2
						(ego posn: 1 (ego y?) view: 82 setStep: 2 1 setPri: 4)
					)
				)
			)
			(48
				(switch global104
					(0 (ego posn: 318 (ego y?)))
					(1
						(ego
							posn: 318 (ego y?)
							looper: (ScriptID DUNE 1)
							setPri: 4
						)
					)
					(2
						(= global104 4)
						(ego posn: 318 (ego y?) view: 68 setStep: 1 1 setPri: 2)
					)
				)
			)
			(51
				(NormalEgo)
				(= global104 0)
				(ego view: 0 posn: (ego x?) 188)
			)
			(else  (ego posn: 154 174))
		)
		(self setRegions: DUNE)
		(switch terminatorState
			(1
				(if (== (Random 1 3) 2)
					((= terminator (Actor new:)) posn: 1000 1000 init:)
					(= notifyCountdown (Random 2 10))
				)
			)
			(2
				((= terminator (Actor new:)) posn: 1000 1000 init:)
				(= notifyCountdown 3)
			)
		)
		(if (cast contains: terminator)
			(terminator
				view: 106
				setCycle: Walk
				observeControl: 2
				posn: 1000 1000
				init:
			)
			(= gGEgoX_4 124)
			(= gGEgoY_3 147)
			(switch prevRoomNum
				(east
					(terminator posn: 359 172)
				)
				(west
					(terminator posn: -40 172)
				)
				(south
					(terminator posn: 160 249)
				)
				(north
					(terminator posn: 124 147)
				)
			)
			(= terminatorState 1)
			((ScriptID DUNE 0) notify: 1)
		else
			((ScriptID DUNE 0) notify: 4)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(= local0 (ego onControl: 0))
		(cond 
			((and (== global116 2) (not (& local0 $0002)))
				(= global116 0)
				(if (or (proc501_2 8) (proc501_2 4))
					(ego posn: (ego x?) (+ (ego y?) 5))
				)
			)
			((and (== global116 4) (not (& local0 $0004))) (= global116 0) (ego ignoreControl: 4))
			((and (== global116 8) (not (& local0 $0008)))
				(= global116 0)
				(if (proc501_2 4)
					(ego posn: (ego x?) (+ (ego y?) 5))
				)
			)
			(
			(and (== global116 16) (not (& local0 $0010))) (ego ignoreControl: 16) (= global116 0))
		)
		(switch global104
			(0
				(cond 
					(
					(and (& local0 $0002) (not isHandsOff) (not global116))
						(= global104 1)
						(= global116 2)
						(= gGEgoX_4 (ego x?))
						(= gGEgoY_3 (ego y?))
						(if (IsObject (ego mover?))
							(if gGEgoY_4
								(= egoY 400)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego
							view: 82
							xStep: 2
							yStep: 1
							looper: (ScriptID DUNE 1)
							setMotion: MoveTo egoX egoY
							setPri: 5
						)
					)
					((and (proc501_2 global109) (== global116 0))
						(= global104 1)
						(= egoY 180)
						(= egoX (ego x?))
						(ego setPri: 3 posn: (ego x?) (+ (ego y?) 8))
						(RedrawCast)
						(ego
							view: 82
							looper: (ScriptID DUNE 1)
							xStep: 2
							yStep: 1
							setMotion: MoveTo egoX egoY
							setPri: 4
						)
					)
				)
			)
			(1
				(cond 
					(
					(and (& local0 $0004) (not isHandsOff) (not global116))
						(= global116 4)
						(= global104 2)
						(if (IsObject (ego mover?))
							(if gGEgoY_4
								(= egoY gGEgoY_4)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego posn: (ego x?) (- (ego y?) 3) observeControl: 4)
						(RedrawCast)
						(ego
							view: 82
							looper: 0
							setStep: 2 1
							observeControl: 4
							setMotion: MoveTo egoX egoY
						)
					)
					(
						(and
							(& local0 $0002)
							(== global104 1)
							(not isHandsOff)
							(not global116)
						)
						(= global116 2)
						(= global104 0)
						(if (IsObject (ego mover?))
							(if gGEgoY_4
								(= egoY gGEgoY_4)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego
							view: 0
							xStep: 3
							yStep: 2
							looper: 0
							setMotion: MoveTo egoX egoY
							setPri: -1
							moveSpeed: 0
						)
					)
					((and (== global116 0) (proc501_2 local3))
						(= egoY 180)
						(= egoX (ego x?))
						(ego setPri: 3 posn: (ego x?) (+ (ego y?) 8))
						(RedrawCast)
						(ego
							view: 82
							looper: (ScriptID DUNE 1)
							xStep: 2
							yStep: 1
							setMotion: MoveTo egoX egoY
							setPri: 4
						)
					)
				)
			)
			(2
				(cond 
					(
					(and (& local0 $0008) (not isHandsOff) (not global116))
						(= global104 3)
						(= global116 8)
						(if (IsObject (ego mover?))
							(if gGEgoY_4
								(= egoY 400)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego
							view: 82
							xStep: 2
							yStep: 1
							looper: (ScriptID DUNE 1)
							setMotion: MoveTo egoX egoY
							setPri: 2
						)
					)
					(
					(and (& local0 $0004) (not isHandsOff) (not global116))
						(= global116 4)
						(= global104 1)
						(if (IsObject (ego mover?))
							(if gGEgoY_4
								(= egoY -400)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego posn: (ego x?) (- (ego y?) 3) observeControl: 4)
						(RedrawCast)
						(ego
							view: 82
							xStep: 2
							yStep: 1
							looper: (ScriptID DUNE 1)
							setMotion: MoveTo egoX egoY
							setPri: 5
						)
					)
					((and (== global116 0) (proc501_2 local3))
						(= global104 3)
						(= egoY 180)
						(= egoX (ego x?))
						(ego setPri: 2 posn: (ego x?) (+ (ego y?) 8))
						(RedrawCast)
						(ego
							view: 82
							looper: (ScriptID DUNE 1)
							xStep: 2
							yStep: 1
							setMotion: MoveTo egoX egoY
							setPri: 2
						)
					)
				)
			)
			(3
				(cond 
					(
					(and (& local0 $0010) (not isHandsOff) (not global116))
						(= global116 16)
						(= global104 4)
						(if (IsObject (ego mover?))
							0
							(if gGEgoY_4
								(= egoY gGEgoY_4)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego posn: (ego x?) (- (ego y?) 3))
						(RedrawCast)
						(ego
							view: 68
							looper: 0
							setStep: 1 1
							observeControl: 16
							setMotion: MoveTo egoX egoY
						)
					)
					(
					(and (& local0 $0008) (not isHandsOff) (not global116))
						(= global116 8)
						(= global104 2)
						(if (IsObject (ego mover?))
							0
							(if gGEgoY_4
								(= egoY gGEgoY_4)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego
							view: 82
							looper: 0
							setStep: 1 1
							setPri: 3
							setMotion: MoveTo egoX egoY
						)
					)
					((and (== global116 0) (proc501_2 local3))
						(= egoY 180)
						(= egoX (ego x?))
						(ego setPri: 2 posn: (ego x?) (+ (ego y?) 8))
						(RedrawCast)
						(ego
							view: 82
							looper: (ScriptID DUNE 1)
							xStep: 2
							yStep: 1
							setMotion: MoveTo egoX egoY
						)
					)
				)
			)
			(4
				(cond 
					(
					(and (& local0 $0010) (not isHandsOff) (not global116))
						(= global116 16)
						(= global104 3)
						(if (IsObject (ego mover?))
							(if gGEgoY_4
								(= egoY -400)
								(= egoX gGEgoX_5)
							else
								(= egoY (+ (- (ego y?) ((ego mover?) y?)) (ego y?)))
								(= egoX ((ego mover?) x?))
							)
						else
							(= egoY (ego y?))
							(= egoX (ego x?))
						)
						(ego view: 82 posn: (ego x?) (- (ego y?) 3))
						(RedrawCast)
						(ego
							xStep: 2
							yStep: 1
							looper: (ScriptID DUNE 1)
							observeControl: 16
							setMotion: MoveTo egoX egoY
							setPri: 2
						)
					)
					((& local0 $0020) (curRoom newRoom: 43))
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 5])
		(if (or (event claimed?) (== isHandsOff 1)) (return 1))
		(return
			(switch (event type?)
				(saidEvent
					(if (Said 'look>')
						(cond 
							(
								(or
									(Said '/area')
									(Said '/around')
									(Said '[<around][/!*]')
								)
								(Print 47 0)
							)
							(
								(Said
									'/android,animal,alien,hill,appendage,head,lip,animal'
								)
								(Print 47 1)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(cast contains: terminator)
				(< (ego distanceTo: terminator) 120)
			)
			(= terminatorState 2)
		)
		(if (not isHandsOff) (super newRoom: newRoomNumber))
	)
)

(instance duneFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 76
					looper: 0
					setStep: 5 5
					setLoop: 0
					setAvoider: Avoider
					setPri: 5
					setMotion: MoveTo 204 170 self
				)
			)
			(1
				(HandsOn)
				(ego view: 82 setLoop: -1 setAvoider: 0 setStep: 1 1)
				(= global104 2)
				(curRoom setScript: 0)
			)
		)
	)
)
