;;; Sierra Script 1.0 - (do not remove this comment)
(script# 49)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room49 0
)

(local
	local0
	twistSound
)
(instance Room49 of Room
	(properties
		picture 49
	)
	
	(method (init)
		(= east 50)
		(= west 52)
		(= north 45)
		(= south 53)
		(= horizon 72)
		(= global109 0)
		(Load SOUND 31)
		(Load SOUND 32)
		(Load VIEW 63)
		(super init:)
		(lightning init:)
		(ego init:)
		(NormalEgo)
		(= global104 0)
		(switch prevRoomNum
			(50
				(cond 
					((< (ego y?) 94) (ego view: 0 posn: 318 (ego y?)))
					((== global104 0) (ego view: 63 posn: 317 (ego y?)))
				)
			)
			(45
				(NormalEgo)
				(ego view: 63 posn: (ego x?) (+ horizon 2))
			)
			(52
				(switch global104
					(0
						(ego posn: 1 (ego y?) view: 63 setPri: -1)
					)
					(1
						(NormalEgo)
						(ego posn: 1 80 view: 0)
					)
					(2
						(NormalEgo)
						(ego posn: 1 80 view: 0)
					)
				)
			)
			(53
				(NormalEgo)
				(HandsOn)
				(ego view: 0 posn: (ego x?) 188)
			)
			(40
				(NormalEgo)
				(= programControl FALSE)
				(HandsOn)
				(ego view: 63 posn: 184 174 loop: 2 init:)
			)
			(14
				(ego view: 63)
				(curRoom setScript: Actions)
				(theMusic owner: -1 number: 22 priority: 1 loop: -1 play:)
				(= programControl TRUE)
			)
			(else 
				(ego view: 63 posn: 160 187 init:)
			)
		)
		(cond 
			(
				(and
					(!= isHandsOff TRUE)
					(!= prevRoomNum 45)
					(ego inRect: -5 0 324 94)
					(!= (curRoom script?) termCatch)
				)
				(ego view: 0)
			)
			((!= (curRoom script?) termCatch) (ego view: 63))
		)
		(self setRegions: DUNE)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if
			(and
				(or (== terminatorState 1) (== terminatorState 2))
				(not (cast contains: terminator))
				(& (ego onControl: 1) $0004)
			)
			(curRoom setScript: termCatch)
		)
		(if
			(and
				(& (ego onControl:) $4000)
				(== script 0)
				(not (cast contains: terminator))
			)
			(curRoom newRoom: 14)
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
								(Print 49 0)
							)
							((Said '/craft,aluminum,aluminum') (Print 49 1))
						)
					)
					((Said 'enter/craft') (Print 49 2))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(NormalEgo)
		(DisposeScript EXTRA)
		(if (== newRoomNumber 14) (theMusic fade:))
		(if (or (not isHandsOff) (== newRoomNumber 40))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance Actions of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 157 143 setMotion: MoveTo 184 174 self)
			)
			(1
				(Print 49 3)
				(RedrawCast)
				(if (== visitedPhleebhut FALSE)
					(= visitedPhleebhut TRUE)
					(Print 49 4 #dispose)
					(= seconds 5)
				else
					(HandsOn)
				)
			)
			(2 (cls) (curRoom newRoom: 40))
		)
	)
)

(instance flash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 20)))
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
			(4 (self init:))
		)
	)
)

(instance termCatch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= terminator (Actor new:))
				(terminator
					view: 106
					setPri: (ego priority?)
					observeControl: 16384
					setLoop: 8
					setCycle: Forward
					init:
				)
				(terminator posn: (ego x?) (ego y?))
				((= twistSound (Sound new:))
					number: 97
					loop: -1
					priority: 99
					play:
				)
				(ego illegalBits: 0 ignoreActors: hide:)
				(= seconds 7)
			)
			(1
				(twistSound stop:)
				(EgoDead 901 0 14 16)
			)
		)
	)
)

(instance lightning of Prop
	(properties
		y 54
		x 48
		view 69
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setScript: flash)
	)
)

(instance thunder of Sound
	(properties)
)
