;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include sci.sh)
(use Main)
(use Intrface)
(use EgoDead)
(use GoToSaid)
(use Track)
(use Follow)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	dullesAirport 0
)

(instance dullesAirport of Rm
	(properties
		picture 17
		vanishingX 154
		vanishingY 35
	)
	
	(method (init)
		(super init:)
		(self setRegions: 302)
		(Load rsVIEW 204 17 417)
		(ego
			view: 204
			posn: 210 175
			init:
			setMotion: MoveTo 210 145
			setScript: keepOutOfAirportScript
		)
		(DirLoop ego 0)
		(globalSound
			number: (SoundFX 64)
			priority: 1
			loop: -1
			play: planeScript
		)
		(plane init:)
		(slidingDoor1 init:)
		(slidingDoor2 init:)
		(limo init: hide: setScript: limoScript)
	)
	
	(method (dispose)
		(ego ignoreActors: 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,airport]') (Print 17 0))
			((Said 'look[<at,through,out]/shutter') (Print 17 1))
			((Said 'look[<up,down,at][/floor,ceiling,wall]') (SeeNothing))
		)
	)
	
	(method (cue)
		(if script (script cue:) else (self newRoom: 18))
	)
)

(instance keepOutOfAirportScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== (ego onControl: 1) 16384)
			(if (not register)
				(switch (Random 0 3)
					(0 (Print 17 2))
					(1 (Print 17 3))
					(2 (Print 17 4))
					(3 (Print 17 5))
				)
				(= register 1)
			)
		else
			(= register 0)
		)
	)
)

(instance limoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 14))
			(1
				(limo show: setMotion: MoveTo 139 124 self)
			)
			(2
				(limo stopUpd:)
				(driver
					init:
					ignoreControl: -32768
					setScript: driverScript
				)
			)
			(3
				(limo setMotion: MoveTo 370 124 self)
			)
			(4
				(globalSound fade: curRoom)
				(= seconds 4)
			)
			(5 (curRoom cue:))
		)
	)
)

(instance driverScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and register (< (client distanceTo: ego) 20))
			(self cue:)
			(ego setMotion: 0)
			(= register 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(driver setPri: 7 setMotion: MoveTo 219 118 self)
			)
			(1
				(driver setPri: -1 setMotion: MoveTo 222 130 self)
			)
			(2 (= register 1))
			(3
				(Print 17 6)
				(driver setScript: waitForResponseScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((< state 2))
			(
				(GoToIfSaid
					client
					event
					client
					15
					'show,give/id,card'
					17
					7
				)
				(= register 0)
			)
			((Said 'show,give/id,card')
				(if (ego has: 2)
					(getEgoIdScript start: 1)
					(driver setScript: getEgoIdScript)
				else
					(Print 17 8)
					(curRoom setScript: endOfGameScript)
					(driver setScript: driverBackScript)
				)
			)
		)
	)
)

(instance waitForResponseScript of Script
	(properties)
	
	(method (handleEvent)
		(cond 
			((Said 'affirmative') (driver setScript: getEgoIdScript))
			((Said 'n')
				(driver setScript: driverBackScript)
				(curRoom setScript: notWestlandScript)
			)
			((Said 'show,give/id,card')
				(if (ego has: 2)
					(getEgoIdScript start: 1)
					(driver setScript: getEgoIdScript)
				else
					(Print 17 8)
					(curRoom setScript: endOfGameScript)
					(driver setScript: driverBackScript)
				)
			)
			((Said 'address/man') (Print 17 6))
		)
	)
)

(instance endOfGameScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1 (EgoDead 918 0 0 17 9))
		)
	)
)

(instance notWestlandScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(Print 17 10)
				(curRoom setScript: endOfGameScript cue:)
			)
		)
	)
)

(instance driverBackScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(driver setLoop: -1 setMotion: MoveTo 219 118 self)
			)
			(1
				(driver setPri: 7 setMotion: MoveTo 147 118 self)
			)
			(2
				(driver setMotion: MoveTo 147 127 self)
			)
			(3
				(driver
					setLoop: 8
					setCel: 0
					posn: 145 97
					setCycle: End self
				)
			)
			(4
				(driver y: 0 dispose:)
				(limo cue:)
			)
		)
	)
)

(instance getEgoIdScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Print 17 11))
			(1
				(Print 17 12)
				(User canInput: 1)
				(Print 17 13)
				(theGame changeScore: 1)
				(= seconds 1)
			)
			(2
				(curRoom setScript: getInLimoScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'show,give/id,card')
				(if (ego has: 2)
					(if (== state 0) (self cue:))
				else
					(Print 17 8)
					(curRoom setScript: endOfGameScript)
					(driver setScript: driverBackScript)
				)
			)
			((Said 'address') (Print 17 14))
		)
	)
)

(instance getInLimoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 202 133 self
				)
			)
			(1
				(ego
					setMotion: MoveTo (- (limo x?) 49) (+ (limo y?) 7) self
				)
				(driver
					illegalBits: 0
					ignoreActors:
					setMotion: Follow ego 40
					setLoop: 5
				)
			)
			(2
				(driver
					setMotion: MoveTo (- (limo x?) 34) (+ (limo y?) 7) self
				)
			)
			(3
				(driver setLoop: 6 cycleSpeed: 2 setCycle: End self)
			)
			(4
				(ego
					view: 417
					setLoop: 7
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(5
				(ego y: 0 dispose:)
				(driver setCycle: Beg self)
			)
			(6
				(driver
					setLoop: 4
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 222 130 self
				)
			)
			(7
				(driver setScript: driverBackScript)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance limo of Act
	(properties
		y 124
		x -50
		view 17
		loop 2
	)
	
	(method (init)
		(self
			setLoop: 2
			ignoreControl: -32768
			stopUpd:
			setPri: -1
		)
		(super init:)
		(if howFast
			(wheel1
				ignoreControl: -32768
				illegalBits: illegalBits
				init:
			)
			(wheel2
				ignoreControl: -32768
				illegalBits: illegalBits
				init:
			)
			(flag
				ignoreControl: -32768
				illegalBits: illegalBits
				init:
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'call/cab[/cab]')
				(cond 
					((& signal $0008) (Print 17 15))
					((OneOf (script state?) 3 4) (Print 17 16))
					(else (Print 17 17))
				)
			)
			((Said '[/limo,car]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (& (limo signal?) $0008)
							(Print 17 18)
						else
							(Print 17 19)
						)
					)
				)
			)
		)
	)
	
	(method (stopUpd)
		(super stopUpd:)
		(if howFast
			(wheel1 stopUpd:)
			(wheel2 stopUpd:)
			(flag stopUpd:)
		)
	)
	
	(method (startUpd)
		(super startUpd:)
		(if howFast
			(wheel1 startUpd:)
			(wheel2 startUpd:)
			(flag startUpd:)
		)
	)
	
	(method (hide)
		(super hide:)
		(if howFast (wheel1 hide:) (wheel2 hide:) (flag hide:))
	)
	
	(method (show)
		(super show:)
		(if howFast (wheel1 show:) (wheel2 show:) (flag show:))
	)
	
	(method (setMotion)
		(super setMotion: &rest)
		(if howFast
			(wheel1 setCycle: Walk setMotion: Track limo -47 6)
			(wheel2 setCycle: Walk setMotion: Track limo 48 6)
			(flag setCycle: Walk setMotion: Track limo -67 -16)
		)
	)
)

(instance driver of Act
	(properties
		y 118
		x 147
		view 417
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/driver,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 17 20))
				)
			)
		)
	)
)

(instance wheel1 of Act
	(properties
		view 17
		loop 4
	)
	
	(method (init)
		(self setLoop: 4 ignoreActors: setPri: 8)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/tire,wheel]>')
				(cond 
					((not y))
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 17 21))
				)
			)
		)
	)
)

(instance wheel2 of Act
	(properties
		view 17
		loop 4
	)
	
	(method (init)
		(self setLoop: 4 ignoreActors: setPri: 8)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/tire,wheel]>')
				(cond 
					((not y))
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 17 21))
				)
			)
		)
	)
)

(instance flag of Act
	(properties
		view 17
		loop 3
	)
	
	(method (init)
		(self setLoop: 3 ignoreActors: setPri: 6)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/flag]>')
				(cond 
					((not y))
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 17 22))
				)
			)
		)
	)
)

(instance slidingDoor1 of Act
	(properties
		y 137
		x 93
		view 17
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			ignoreControl: -32768
			setPri: 10
			setLoop:
			stopUpd:
		)
		(auxDoor1
			init:
			setLoop:
			ignoreActors:
			ignoreControl: -32768
			setPri: 10
			stopUpd:
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (not (InRect 45 120 170 160 ego)) (== x 64))
				(slidingDoor1 setMotion: MoveTo 93 137 self)
				(auxDoor1 setMotion: MoveTo 148 137 auxDoor1)
			)
			((and (InRect 48 124 165 154 ego) (== x 93))
				(slidingDoor1 setMotion: MoveTo 64 137 self)
				(auxDoor1 setMotion: MoveTo 177 137 auxDoor1)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look') (Print 17 23))
					((Said 'open') (Print 17 24))
					((Said 'close') (Print 17 25))
				)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance auxDoor1 of Act
	(properties
		y 137
		x 148
		view 17
		loop 1
		priority 10
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance slidingDoor2 of Act
	(properties
		y 137
		x 175
		view 17
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setPri: 10
			ignoreControl: -32768
			setLoop:
			stopUpd:
		)
		(auxDoor2
			ignoreActors:
			setPri: 10
			ignoreControl: -32768
			init:
			setLoop:
			stopUpd:
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (not (InRect 120 120 290 160 ego)) (== x 146))
				(self setMotion: MoveTo 175 137 self)
				(auxDoor2 setMotion: MoveTo 230 137 auxDoor2)
			)
			((and (InRect 130 124 284 154 ego) (== x 175))
				(self setMotion: MoveTo 146 137 self)
				(auxDoor2 setMotion: MoveTo 259 137 auxDoor2)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look') (Print 17 23))
					((Said 'open') (Print 17 24))
					((Said 'close') (Print 17 25))
				)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance auxDoor2 of Act
	(properties
		y 137
		x 230
		view 17
		loop 1
		priority 10
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance plane of Act
	(properties
		yStep 1
		view 17
		loop 5
		signal $0800
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: planeScript)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((not (OneOf (script state?) 2 3)))
			(
			(TurnIfSaid self event 'look[<at]/airplane,airplane,jet'))
			((Said 'look[<at][/airplane,airplane,jet]') (Print 17 26))
		)
	)
)

(instance planeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds 3))
			(2
				(plane
					posn: 26 53
					setMotion: MoveTo 300 (+ 60 (Random 0 14)) self
				)
			)
			(3 (self init: client))
		)
	)
)
