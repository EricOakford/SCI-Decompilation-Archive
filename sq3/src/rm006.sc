;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm006 0
)

(local
	shadowVisible
)
(instance rm006 of Room
	(properties
		picture 6
		style HSHUTTER
		horizon 10
	)
	
	(method (init &tmp [temp0 50])
		(self setLocales: JUNKBAY)
		(Load VIEW 7)
		(Load VIEW 15)
		(Load VIEW 18)
		(Load VIEW 751)
		(Load VIEW 192)
		(Load VIEW 0)
		(Load SOUND 77)
		(ego view: 0 setStep: -1 1)
		(super init:)
		(ego
			setAvoider: (Avoider new:)
			x: (if (== prevRoomNum 5) -6 else 326)
			y: 147
			setMotion: MoveTo (if (== prevRoomNum 5) 400 else -400) 147
			init:
		)
		(User prevDir: (if (== prevRoomNum 5) 3 else 7))
		(shadow init:)
		(if (InRoom 1) (wire init:))
	)
	
	(method (doit)
		(cond 
			(
				(and
					(ego has: iReactor)
					(ego inRect: 160 146 171 150)
					(not ratStoleReactor)
					(not (cast contains: rat))
				)
				(rat init:)
			)
			((< (ego x?) -6) (curRoom newRoom: 5))
			((> (ego x?) 326) (curRoom newRoom: (if global157 27 else 7)))
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							(
								(or
									(Said '[<up,above,at]/ceiling')
									(Said '<up,above[/ceiling]')
								)
								(Print 6 0)
							)
							((Said '/partition') (Print 6 1))
							((Said '/door,console[<absent]') (Print 6 2))
							((Said '<below/console[<absent]') (Print 6 3))
							(
								(or
									(Said '[<down,below,at]/dirt,deck')
									(Said '<down,below[/dirt,deck]')
								)
								(Print 6 4)
							)
							(
							(Said '[<around,at,in][/area,tube,cave,pit,stage,!*]') (Print 6 5))
							((Said '/cable<left,fine') (if (InRoom 1) (Print 6 6)))
							((Said '/cable[<bad,jagged,all]') (if (InRoom 1) (Print 6 7) else (Print 6 8)))
							((Said '[<in,at,through,in]/grate') (Print 6 9))
							((Said '/mice,animal')
								(cond 
									((cast contains: rat) (Print 6 10))
									(ratStoleReactor (Print 6 11))
									(else (Print 6 12))
								)
							)
							((Said '[<in,in,through,at]/cavity') (Print 6 13))
							((Said '/conduit') (Print 6 14))
						)
					)
					((Said 'climb/craft,pit,craft,side') (Print 6 15))
					((Said 'get,(drag[<on])/cable')
						(if (InRoom iWire)
							(if (ego inRect: 64 147 85 149)
								(self setScript: wireScript)
							else
								(Print 6 16)
							)
						else
							(Print 6 17)
						)
					)
					((Said 'get/conduit') (Print 6 18))
					(
						(Said
							'get,climb,crawl,conceal[<in,in,through,below]/grate'
						)
						(Print 6 19)
					)
					(
						(Said
							'press,hoist,manipulate,open,get,remove/console,(iron[<mushroom])'
						)
						(Print 6 20)
					)
				)
			)
			(mouseDown
				(if (== (User controls?) 0) (return))
				(ego
					setMotion:
						MoveTo
						(cond 
							((< (event x?) 30) -30)
							((> (event x?) 295) 330)
							(else (event x?))
						)
						(ego y?)
				)
				(event claimed: TRUE)
			)
			(direction
				(if (== (User controls?) 0) (return))
				(switch (event message?)
					(dirN
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirS
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirNW
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirNE
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirSE
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
					(dirSW
						(ego setMotion: 0)
						(event claimed: TRUE)
						(return)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance wireScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 192
					loop: (if (< (ego x?) (wire x?)) 1 else 0)
					cel: 0
					setMotion: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(1 (= cycles 5))
			(2
				(wire dispose:)
				(ego get: 1)
				(= cycles 2)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego view: 0 cycleSpeed: 0 setCycle: Walk)
				(= cycles 2)
				(theGame changeScore: 5)
			)
			(5
				(Print 6 21)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance shadowScript of Script
	(properties)
	
	(method (doit)
		(if
			(or
				(ego inRect: -9 145 56 150)
				(ego inRect: 281 145 329 150)
			)
			(shadow loop: (ego loop?) cel: (ego cel?))
			(if (ego inRect: -9 145 56 150)
				(shadow view: 7)
			else
				(shadow view: 18)
			)
			(shadow
				posn:
					(if (ego inRect: -9 145 56 150)
						(+ (ego x?) 12)
					else
						(- (ego x?) 12)
					)
					147
			)
			(if (!= shadowVisible TRUE) (shadow show:) (= shadowVisible TRUE))
		else
			(shadow hide:)
			(= shadowVisible FALSE)
		)
		(super doit:)
	)
)

(instance ratScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rat setMotion: MoveTo 197 (+ (ego y?) 4) self)
				(= ratStoleReactor 1)
			)
			(1
				(User canInput: FALSE)
				(rat
					setStep: -1 5
					setLoop: -1
					loop: 1
					setCycle: Walk
					setMotion: Chase ego 4 self
				)
			)
			(2
				(HandsOff)
				(rumbleSound play:)
				(ego setMotion: 0)
				(rat
					setStep: 1 1
					posn: (ego x?) (- (ego y?) 4)
					setLoop: 3
					setCycle: Forward
					setPri: 14
				)
				(= seconds 2)
			)
			(3 (biff init:) (= seconds 2))
			(4
				(biff setCel: 1 posn: (+ (rat x?) 17) (- (rat y?) 12))
				(= seconds 2)
				(if (ego has: iReactor)
					(PutInRoom iReactor 15)
					(theGame changeScore: -15)
				)
				(if (ego has: iWire)
					(PutInRoom iWire 15)
					(theGame changeScore: -5)
				)
			)
			(5
				(biff dispose:)
				(= seconds 2)
			)
			(6
				(ego view: 751 loop: 0)
				(rat
					setLoop: 0
					x: (+ (ego x?) 13)
					y: (+ (ego y?) 4)
					setStep: 10
					setMotion: MoveTo 310 (+ (ego y?) 4) self
				)
				(rumbleSound stop:)
			)
			(7
				(rat dispose:)
				(RedrawCast)
				(HandsOn)
				(ego view: 0 setDirection: 0)
				(RedrawCast)
				(Print 6 22)
			)
		)
	)
)

(instance rat of Actor
	(properties
		view 15
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			illegalBits: 0
			setLoop: 2
			posn: 197 78
			setStep: 5 15
			setMotion: MoveTo 197 (+ (ego y?) 4) self
			setScript: ratScript
		)
	)
)

(instance wire of View
	(properties
		view 192
		loop 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: TRUE posn: 77 124)
	)
)

(instance shadow of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: (if (== prevRoomNum 5) 7 else 18)
			setLoop: (if (== prevRoomNum 5) 0 else 1)
			ignoreActors: TRUE
			x: (if (== prevRoomNum 5) 6 else 314)
			y: 147
			setPri: 4
			setScript: shadowScript
			ignoreActors: TRUE
		)
	)
)

(instance biff of View
	(properties
		view 15
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 4
			setCel: 0
			setPri: 15
			ignoreActors: TRUE
			posn: (- (rat x?) 15) (- (rat y?) 22)
		)
	)
)

(instance rumbleSound of Sound
	(properties
		number 77
		priority 5
		loop -1
	)
)
