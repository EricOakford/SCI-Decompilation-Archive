;;; Sierra Script 1.0 - (do not remove this comment)
(script# 48)
(include game.sh)
(use Main)
(use Intrface)
(use Dune)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room48 0
)

(local
	local0
	egoY
	egoX
)
(instance lightning of Prop
	(properties)
)

(instance thunder of Sound
	(properties)
)

(instance Room48 of Room
	(properties
		picture 48
	)
	
	(method (init)
		(= north 44)
		(= west 47)
		(= east 45)
		(= south 52)
		(= global591 (= global109 5))
		(Load SOUND 31)
		(Load SOUND 32)
		(super init:)
		(= global116 0)
		(ego init:)
		(lightning
			view: 69
			loop: 0
			cel: 0
			posn: 80 10
			setScript: flash
			init:
		)
		(switch prevRoomNum
			(north
				(= global104 2)
				(ego
					posn: 112 86
					view: 68
					looper: 0
					setPri: 2
					ignoreControl: 8192
				)
			)
			(south
				(= global104 0)
				(NormalEgo)
				(ego
					posn: (ego x?) 188
					view: 0
					looper: 0
					setPri: -1
					observeControl: 8192
				)
			)
			(west
				(switch global104
					(0
						(ego
							posn: 1 (ego y?)
							setStep: 3 2
							observeControl: 8192
							setPri: -1
						)
					)
					(1
						(ego
							posn: 1 122
							setStep: 2 1
							view: 82
							ignoreControl: 8192
							looper: (ScriptID 501 1)
							setPri: 2
						)
					)
					(3
						(= global104 1)
						(ego
							posn: 1 122
							setStep: 2 1
							view: 82
							ignoreControl: 8192
							looper: (ScriptID 501 1)
							setPri: 2
						)
					)
					(2
						(= global104 1)
						(ego
							posn: 1 122
							setStep: 2 1
							view: 82
							ignoreControl: 8192
							looper: (ScriptID 501 1)
							setPri: 2
						)
					)
					(4
						(= global104 2)
						(ego
							view: 68
							posn: 1 98
							looper: 0
							ignoreControl: 8192
							setStep: 1 1
							setPri: 2
						)
					)
				)
			)
			(east
				(switch global104
					(0
						(NormalEgo)
						(ego
							posn: 318 (ego y?)
							observeControl: 8192
							setStep: 3 2
						)
					)
					(1
						(ego
							posn: 318 111
							setPri: 2
							setStep: 2 1
							looper: (ScriptID 501 1)
						)
					)
					(2
						(ego
							view: 68
							posn: 318 89
							looper: 0
							setStep: 1 1
							setPri: 2
						)
					)
				)
			)
			(else 
				(ego view: 0 posn: 160 160)
			)
		)
		(self setRegions: 501)
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
			(= gGEgoX_4 47)
			(= gGEgoY_3 109)
			(switch prevRoomNum
				(east
					(terminator posn: 359 150)
				)
				(west
					(terminator posn: -40 150)
				)
				(south
					(terminator posn: 213 249)
				)
				(north
					(terminator posn: 47 109)
				)
			)
			(= terminatorState 1)
			((ScriptID 501 0) notify: 1)
		else
			((ScriptID 501 0) notify: 4)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(cond 
			((& (= local0 (ego onControl: 0)) $0008) (curRoom newRoom: 44))
			((and (== global116 2) (not (& local0 $0002)))
				(= global116 0)
				(if (proc501_2 4)
					(ego posn: (ego x?) (+ (ego y?) 5))
				)
			)
			((and (== global116 4) (not (& local0 $0004))) (ego ignoreControl: 4) (= global116 0))
			(
				(and
					(& local0 $0002)
					(== global104 0)
					(not isHandsOff)
					(not global116)
				)
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
					looper: (ScriptID 501 1)
					xStep: 2
					yStep: 1
					setMotion: MoveTo egoX egoY
					setPri: 4
					ignoreControl: 8192
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
					observeControl: 8192
					moveSpeed: 0
				)
			)
			(
				(and
					(== global104 1)
					(& local0 $0004)
					(not isHandsOff)
					(not global116)
				)
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
				(ego
					view: 68
					posn: (ego x?) (- (ego y?) 3)
					observeControl: 4
				)
				(RedrawCast)
				(ego
					view: 68
					looper: 0
					setStep: 1 1
					setPri: 4
					setMotion: MoveTo egoX egoY
				)
			)
			(
				(and
					(== global104 2)
					(& local0 $0004)
					(not isHandsOff)
					(not global116)
				)
				(= global104 1)
				(= global116 4)
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
				(ego
					view: 82
					posn: (ego x?) (- (ego y?) 3)
					observeControl: 4
				)
				(RedrawCast)
				(ego
					looper: (ScriptID 501 1)
					xStep: 2
					yStep: 1
					setMotion: MoveTo egoX egoY
					setPri: 4
					ignoreControl: 8192
				)
			)
			(
				(and
					(!= global104 2)
					(== global116 0)
					(or (proc501_2 global109) (< (ego y?) 85))
				)
				(= global104 1)
				(= egoY 180)
				(= egoX (ego x?))
				(ego setPri: 3 posn: (ego x?) (+ (ego y?) 8))
				(RedrawCast)
				(ego
					view: 82
					looper: (ScriptID 501 1)
					xStep: 2
					yStep: 1
					setMotion: MoveTo egoX egoY
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 5])
		(if (event claimed?) (return TRUE))
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
								(Print 48 0)
							)
							((Said '/rock') (Print 48 1))
							((Said '/cavity') (Print 48 2))
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(DisposeScript 988)
		(NormalEgo)
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

(instance flash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 8 30)))
			(1
				(lightning cel: 255 setCycle: EndLoop self)
			)
			(2
				(lightning cel: 0)
				(= seconds (Random 2 5))
			)
			(3
				(thunder priority: 2 number: (Random 31 32) play: self)
			)
			(4 (= state -1) (= cycles 2))
		)
	)
)
