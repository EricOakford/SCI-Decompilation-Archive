;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
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
	Room45 0
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

(instance Room45 of Room
	(properties
		picture 45
	)
	
	(method (init)
		(= north 41)
		(= west 48)
		(= south 49)
		(= east 46)
		(= global591 (= global109 17))
		(self setRegions: DUNE)
		(Load SOUND 31)
		(Load SOUND 32)
		(super init:)
		(= global116 0)
		(ego init:)
		(lightning
			view: 69
			loop: 4
			cel: 0
			posn: 218 54
			setScript: flash
			init:
		)
		(switch prevRoomNum
			(41
				(= global104 2)
				(ego
					posn: 165 55
					view: 68
					setPri: 4
					looper: 0
					setStep: 1 1
				)
			)
			(49
				(= global104 0)
				(ego view: 0)
				(ego posn: (ego x?) 188)
			)
			(46
				(switch global104
					(0
						(ego posn: 318 (ego y?) setStep: 3 2 setPri: -1)
					)
					(3
						(= global104 1)
						(ego
							posn: 318 100
							setPri: 4
							setStep: 2 1
							looper: (ScriptID DUNE 1)
							ignoreControl: 8192
						)
					)
					(4
						(= global104 2)
						(ego
							view: 68
							posn: 318 80
							looper: 0
							setStep: 1 1
							setPri: 4
						)
					)
				)
			)
			(48
				(switch global104
					(0
						(NormalEgo)
						(ego view: 0 posn: 1 (ego y?))
					)
					(1
						(ego
							posn: 1 111
							setPri: 4
							setStep: 2 1
							looper: (ScriptID DUNE 1)
							ignoreControl: 8192
						)
					)
					(2
						(ego view: 68 posn: 1 89 looper: 0 setStep: 1 1 setPri: 4)
					)
				)
			)
			(else 
				(ego view: 0 posn: 160 160)
			)
		)
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
				hide:
				init:
			)
			(= gGEgoX_4 113)
			(= gGEgoY_3 113)
			(switch prevRoomNum
				(east
					(terminator posn: 359 140)
				)
				(west
					(terminator posn: -40 140)
				)
				(south
					(terminator posn: 160 249)
				)
				(north
					(terminator posn: 113 113)
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
		(cond 
			((& (= local0 (ego onControl: 0)) $0008) (curRoom newRoom: 41))
			(
				(and
					(== global116 2)
					(not isHandsOff)
					(not (& local0 $0002))
				)
				(= global116 0)
				(if (proc501_2 16)
					(ego posn: (ego x?) (+ (ego y?) 5))
				)
			)
			(
				(and
					(== global116 4)
					(not isHandsOff)
					(not (& local0 $0004))
				)
				(= global116 0)
				(ego ignoreControl: 4)
			)
			(
				(and
					(& local0 $0002)
					(not isHandsOff)
					(== global104 0)
					(not global116)
				)
				(= global104 1)
				(= gGEgoX_4 (ego x?))
				(= gGEgoY_3 (ego y?))
				(= global116 2)
				(if (IsObject (ego mover?))
					(if gGEgoY_4
						(= egoY 400)
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
					looper: (ScriptID DUNE 1)
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
					(not isHandsOff)
					(== global104 1)
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
					(not isHandsOff)
					(& local0 $0004)
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
					setStep: 1 1
					looper: 0
					setPri: 4
					setMotion: MoveTo egoX egoY
				)
			)
			(
				(and
					(== global104 2)
					(not isHandsOff)
					(& local0 $0004)
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
					view: 82
					looper: (ScriptID DUNE 1)
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
					(proc501_2 global109)
				)
				(= global104 1)
				(= egoY 180)
				(= egoX (ego x?))
				(ego setPri: 4 posn: (ego x?) (+ (ego y?) 8))
				(RedrawCast)
				(ego
					view: 82
					looper: (ScriptID DUNE 1)
					xStep: 4
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
					(if
						(and
							(Said 'look>')
							(or
								(Said '/area')
								(Said '/around')
								(Said '[<around][/!*]')
							)
						)
						(Print 45 0)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(NormalEgo)
		(DisposeScript EXTRA)
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
			(4 (= state -1))
		)
	)
)
