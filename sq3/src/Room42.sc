;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room42 0
)

(local
	fester
)
(instance lightning of Prop
	(properties)
)

(instance thunder of Sound
	(properties)
)

(instance Room42 of Room
	(properties
		picture 42
	)
	
	(method (init)
		(= east 43)
		(= south 46)
		(= west 41)
		(= north 54)
		(= horizon 94)
		(= global109 0)
		(= global591 0)
		(= global104 0)
		(super init:)
		(ego init:)
		(Load SOUND 31)
		(Load SOUND 32)
		(lightning
			view: 69
			loop: 6
			cel: 0
			posn: 123 59
			setScript: flash
			init:
		)
		(switch prevRoomNum
			(41
				(NormalEgo)
				(= global104 0)
				(if (< (ego y?) (+ horizon 2))
					(ego posn: 1 (+ horizon 2))
				else
					(ego posn: 1 (ego y?))
				)
			)
			(46
				(NormalEgo)
				(= global104 0)
				(ego view: 0 posn: (ego x?) 188)
			)
			(43
				(NormalEgo)
				(ego view: 0 posn: 318 (ego y?))
			)
			(420
				(NormalEgo)
				(theMusic number: 22 priority: 0 loop: -1 play:)
				(ego view: 0 loop: 1 posn: 266 107)
			)
			(54
				(ego posn: (ego x?) (+ horizon 2))
			)
			(421
				(curRoom setScript: festerOut)
			)
			(else 
				(ego posn: 188 160 view: 0)
			)
		)
		(ego init:)
		(self setRegions: DUNE)
		(switch terminatorState
			(1
				(if (== (Random 1 4) 3)
					(Load VIEW 106)
					((= terminator (Actor new:)) posn: 1000 1000 init:)
					(= notifyCountdown (Random 2 12))
				)
			)
			(2
				((= terminator (Actor new:)) posn: 1000 1000 init:)
				(= notifyCountdown 3)
			)
		)
		(cond 
			((cast contains: terminator)
				(terminator
					view: 106
					setCycle: Walk
					observeControl: 2
					posn: 1000 1000
					init:
				)
				(switch prevRoomNum
					(east
						(terminator posn: 359 167)
					)
					(west
						(terminator posn: -40 143)
					)
					(south
						(terminator posn: 160 249)
					)
					(north
						(terminator posn: 160 (+ horizon 2))
					)
				)
				(= terminatorState 1)
				((ScriptID DUNE 0) notify: 1)
			)
			((== (curRoom script?) 0) ((ScriptID 501 0) notify: 4))
		)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (& (ego onControl: origin) $0040)
			(curRoom newRoom: 420)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							(
								(or
									(Said '/area')
									(Said '/around')
									(Said '[<around][/!*]')
								)
								(Print 42 0)
							)
							((Said '/feet,toe,leg') (Print 42 1))
							((Said '/entrance,cavity') (Print 42 2))
							((Said '/door,door') (Print 42 3))
							((Said '/mog,animal') (Print 42 4))
							((Said '/sign') (Print 42 5))
						)
					)
					((Said 'get,drag,beat/sign,mog') (Print 42 6))
					((Said 'read/sign')
						(cond 
							((& (ego onControl: 0) $2000)
								(if (or (== (ego loop?) 0) (== (ego loop?) 2))
									(Print 42 7 #mode 1 #at -1 100)
								else
									(Print 42 8)
								)
							)
							((& (ego onControl: 0) $1000)
								(if (!= (ego loop?) 3)
									(Print 42 8)
								else
									(Print 42 9 #at -1 100)
								)
							)
							(else (Print 42 10))
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== newRoomNumber 420) (theMusic fade:))
		(if
			(and
				(cast contains: terminator)
				(< (ego distanceTo: terminator) 120)
			)
			(= terminatorState terminatorSEARCHING)
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

(instance festerOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(theMusic number: 22 priority: 0 loop: -1 play:)
				(= fester (Actor new:))
				(fester
					view: 67
					setLoop: 0
					setCycle: Walk
					posn: 244 128
					setMotion: MoveTo 329 175 self
					init:
				)
				(NormalEgo)
				(ego view: 0 loop: 1 posn: 266 107 init:)
			)
			(1 (fester dispose:))
		)
	)
)
