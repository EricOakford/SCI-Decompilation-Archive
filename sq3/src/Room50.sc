;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
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
	Room50 0
)

(local
	local0
	egoY
	egoX
	theTheGGEgoX_5
	local4
	local5
)
(instance lightning of Prop
	(properties)
)

(instance thunder of Sound
	(properties)
)

(instance Room50 of Room
	(properties
		picture 50
	)
	
	(method (init)
		(= east 51)
		(= west 49)
		(= south 53)
		(= north 47)
		(= global109 1)
		(= global591 1)
		(= global116 0)
		(Load SOUND 31)
		(Load SOUND 32)
		(super init:)
		(ego view: 0 init:)
		(lightning
			view: 69
			loop: 4
			cel: 0
			posn: 185 58
			setScript: flash
			init:
		)
		(switch prevRoomNum
			(46
				(= global104 2)
				(ego
					posn: 186 67
					view: 68
					looper: 0
					setPri: 1
					setStep: 1 1
					ignoreControl: 8192
				)
			)
			(49
				(ego observeControl: 8192)
				(if (and (> (ego y?) 146) (< (ego y?) 155))
					(ego posn: 1 144)
				else
					(ego posn: 1 (ego y?))
				)
			)
			(51
				(switch global104
					(1
						(ego
							view: 82
							posn: 317 (ego y?)
							ignoreControl: 8192
							setStep: 1 1
							setPri: 1
						)
					)
					(2
						(ego view: 68 posn: 317 (ego y?) setStep: 1 1 setPri: 1)
					)
					(0
						(ego
							view: 0
							posn: 317 (ego y?)
							setStep: 3 2
							observeControl: 8192
							setPri: -1
						)
					)
				)
			)
			(53
				(NormalEgo)
				(ego view: 0 posn: (ego x?) 188 observeControl: 8192)
			)
			(else 
				(ego posn: 180 98 observeControl: 8192)
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
			(= gGEgoX_4 176)
			(= gGEgoY_3 85)
			(switch prevRoomNum
				(east
					(terminator posn: 359 117)
				)
				(west
					(terminator posn: -40 117)
				)
				(south
					(terminator posn: 160 249)
				)
				(north
					(terminator posn: 176 85)
				)
			)
			(= terminatorState 1)
			((ScriptID 501 0) notify: 1)
		else
			((ScriptID 501 0) notify: 4)
		)
		(ego observeControl: 1024)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(= local0 (ego onControl: 0))
		(if (IsObject (ego mover?))
			(= theTheGGEgoX_5 ((ego mover?) x?))
			(= local4 ((ego mover?) y?))
			(= local5 1)
		else
			(= local5 0)
		)
		(cond 
			((and (== global116 2) (not (& local0 $0002)))
				(= global116 0)
				(if (proc501_2 1)
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
				(= global116 2)
				(= global104 1)
				(= gGEgoX_4 (ego x?))
				(= gGEgoY_3 (ego y?))
				(if local5
					(if gGEgoY_4
						(= egoY 400)
						(= egoX gGEgoX_5)
					else
						(= egoY (+ (- (ego y?) local4) (ego y?)))
						(= egoX theTheGGEgoX_5)
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
					setPri: 1
					ignoreControl: 8192
				)
			)
			((and (& local0 $0010) (== global104 2)) (curRoom newRoom: 46))
			(
				(and
					(& local0 $0002)
					(== global104 1)
					(not isHandsOff)
					(not global116)
				)
				(= global116 2)
				(= global104 0)
				(if local5
					(if gGEgoY_4
						(= egoY gGEgoY_4)
						(= egoX gGEgoX_5)
					else
						(= egoY (+ (- (ego y?) local4) (ego y?)))
						(= egoX theTheGGEgoX_5)
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
				(if local5
					(if gGEgoY_4
						(= egoY gGEgoY_4)
						(= egoX gGEgoX_5)
					else
						(= egoY (+ (- (ego y?) local4) (ego y?)))
						(= egoX theTheGGEgoX_5)
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
					looper: 0
					setStep: 1 1
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
				(if local5
					(if gGEgoY_4
						(= egoY -400)
						(= egoX gGEgoX_5)
					else
						(= egoY -400)
						(= egoX theTheGGEgoX_5)
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
					setStep: 2 1
					setMotion: MoveTo egoX egoY
					setPri: 1
					ignoreControl: 8192
				)
			)
			(
				(and
					(!= global104 2)
					(== global116 0)
					(or
						(proc501_2 global109)
						(and (> (ego x?) 217) (< (ego y?) 80))
						(and
							(> (ego x?) 87)
							(< (ego x?) 108)
							(< (ego y?) 60)
						)
						(< (ego y?) 54)
					)
				)
				(= global104 1)
				(= egoY 180)
				(= egoX (ego x?))
				(ego setPri: 1 posn: (ego x?) (+ (ego y?) 8))
				(RedrawCast)
				(ego
					view: 82
					looper: (ScriptID 501 1)
					xStep: 2
					yStep: 1
					setMotion: MoveTo egoX egoY
					ignoreControl: 8192
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 5])
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(saidEvent
					(cond 
						((Said 'look>')
							(cond 
								(
									(or
										(Said '/area')
										(Said '/around')
										(Said '[<around][/!*]')
									)
									(Print 50 0)
								)
								((Said '/rock') (Print 50 1))
								((Said '/dune') (Print 50 2))
								((Said '/cavity') (Print 50 3))
							)
						)
						((Said '/cavity') (Print 50 4))
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(NormalEgo)
		(DisposeScript 988)
		(if
			(and
				(cast contains: terminator)
				(< (ego distanceTo: terminator) 85)
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
