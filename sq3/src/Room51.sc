;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
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
	Room51 0
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

(instance Room51 of Room
	(properties
		picture 51
	)
	
	(method (init)
		(= north 47)
		(= west 50)
		(= east 52)
		(= south 53)
		(= horizon 70)
		(= global109 1)
		(= global591 1)
		(Load SOUND 31)
		(Load SOUND 32)
		(super init:)
		(= global116 0)
		(NormalEgo)
		(ego view: 0 init:)
		(lightning
			view: 69
			loop: 4
			cel: 0
			posn: 114 53
			setScript: flash
			init:
		)
		(switch prevRoomNum
			(47
				(= global104 2)
				(ego
					posn: 145 (+ horizon 2)
					setPri: 1
					view: 68
					looper: 0
					setStep: 1 1
					illegalBits: 0
				)
			)
			(50
				(switch global104
					(0
						(if (< (ego y?) 85)
							(ego
								posn: 1 90
								setPri: -1
								setStep: 3 2
								observeControl: 8192
							)
						else
							(ego
								posn: 1 (ego y?)
								setStep: 3 2
								setPri: -1
								observeControl: 8192
							)
						)
					)
					(1
						(ego
							view: 82
							looper: (ScriptID 501 1)
							posn: 1 108
							setPri: 1
							setStep: 1 1
							ignoreControl: 8192
						)
					)
					(2
						(ego
							view: 68
							posn: 1 80
							setStep: 1 1
							setPri: 1
							ignoreControl: 8192
						)
					)
				)
			)
			(52
				(switch global104
					(0
						(NormalEgo)
						(ego view: 0 observeControl: 8192 posn: 318 (ego y?))
					)
					(1
						(ego
							view: 82
							ignoreControl: 8192
							posn: 318 119
							setStep: 1 1
							looper: (ScriptID 501 1)
							setPri: 1
						)
					)
					(2
						(ego
							view: 68
							posn: 318 80
							setStep: 1 1
							setPri: 1
							ignoreControl: 8192
						)
					)
				)
			)
			(53
				(NormalEgo)
				(ego view: 0 posn: (ego x?) 188 observeControl: 8192)
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
				observeControl: 2 8192
				posn: 1000 1000
				init:
			)
			(= gGEgoX_4 127)
			(= gGEgoY_3 103)
			(switch prevRoomNum
				(east
					(terminator posn: 359 135)
				)
				(west
					(terminator posn: -40 116)
				)
				(south
					(terminator posn: 190 249)
				)
				(north
					(terminator posn: 127 103)
				)
			)
			(= terminatorState 1)
			((ScriptID 501 0) notify: 1)
		else
			((ScriptID 501 0) notify: 4)
		)
		(curRoom setScript: Actions)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(= local0 (ego onControl: 0))
		(cond 
			((and (== global116 2) (not (& local0 $0002)))
				(if (proc501_2 1)
					(ego posn: (ego x?) (+ (ego y?) 5))
				)
				(= global116 0)
			)
			((and (== global116 4) (not (& local0 $0004))) (= global116 0) (ego ignoreControl: 4))
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
					setStep: 2 1
					setMotion: MoveTo egoX egoY
					setPri: 1
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
				(= global104 0)
				(= global116 2)
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
					setStep: 3 2
					setMotion: MoveTo egoX egoY
					setPri: -1
					observeControl: 8192
					looper: 0
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
				(ego view: 68 posn: (ego x?) (- (ego y?) 2))
				(RedrawCast)
				(ego
					view: 68
					setStep: 1 1
					looper: 0
					illegalBits: 0
					observeControl: 4
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
				(ego
					view: 82
					posn: (ego x?) (- (ego y?) 2)
					observeControl: 4
				)
				(RedrawCast)
				(ego
					looper: (ScriptID 501 1)
					setStep: 2 1
					illegalBits: -32768
					ignoreControl: 8192
					setMotion: MoveTo egoX egoY
					setPri: 1
				)
			)
			(
				(and
					(!= global104 2)
					(== global116 0)
					(or
						(proc501_2 global109)
						(and
							(> (ego x?) 146)
							(< (ego x?) 179)
							(< (ego y?) 96)
						)
						(< (ego y?) 81)
					)
				)
				(= global104 1)
				(= egoY 180)
				(= egoX (ego x?))
				(ego setPri: 2 posn: (ego x?) (+ (ego y?) 8))
				(RedrawCast)
				(ego
					posn: (ego x?) (+ (ego y?) 8)
					view: 82
					looper: (ScriptID 501 1)
					setStep: 2 1
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
								(Print 51 0)
							)
							((Said '/cavity') (Print 51 1))
							((Said '/rock') (Print 51 2))
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not isHandsOff)
			(NormalEgo)
			(DisposeScript EXTRA)
			(if
				(and
					(cast contains: terminator)
					(< (ego distanceTo: terminator) 120)
				)
				(= terminatorState 2)
			)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(else )
		)
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
			(4 (= state -1))
		)
	)
)
