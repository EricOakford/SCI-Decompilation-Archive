;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use EgoDead)
(use LoadMany)
(use Sight)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	honoluluAirportRm 0
)

(instance honoluluAirportRm of Room
	(properties
		picture 22
	)
	
	(method (init)
		(super init:)
		(self setRegions: 302)
		(LoadMany VIEW 22 17)
		(globalSound
			number: 84
			owner: theGame
			priority: 1
			loop: -1
			play:
		)
		(ego init: posn: 190 169 setScript: walkToDriverScript)
		(plane init:)
		(slidingDoor init:)
		(driver init:)
		(doorProp init:)
		(addToPics add: alohaSignPic limoPic doit:)
	)
	
	(method (dispose)
		(globalSound fade:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene]')
				(Print 22 0)
			)
			((Said 'look/airport')
				(Print 22 1)
			)
			((Said 'look/car')
				(Print 22 2)
			)
			((Said 'open/door')
				(Print 22 3)
			)
		)
	)
)

(instance walkToDriverScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setMotion: MoveTo (+ (driver x?) 30) (+ (driver y?) 2) self
				)
			)
			(1
				(User canInput: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance driver of Actor
	(properties
		y 164
		x 60
		view 22
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/man,driver]>')
				(cond 
					((Said 'look')
						(Print 22 4)
					)
					((Said 'salute')
						(Print 22 5)
					)
					((Said 'address')
						(Print 22 6)
						(self setScript: driverResponseScript)
					)
				)
			)
			((Said 'show/order')
				(Print 22 6)
				(self setScript: driverResponseScript)
			)
		)
	)
)

(instance driverResponseScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(curRoom setScript: newRoomScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'affirmative')
				(if (== state 0)
					(self setScript: showIdScript self)
				else
					(Print 22 7)
				)
			)
			((Said 'show/id,order')
				(Print 22 8)
			)
			((Said 'n')
				(curRoom setScript: endOfGameScript 0 0)
			)
		)
	)
)

(instance showIdScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 22 9)
			)
			(1
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((or (Said 'show/order') (Said 'affirmative'))
				(if (ego has: iEnvelope)
					(Print 22 10)
					(self cue:)
				else
					(switch (Random 0 1)
						(0 (Print 22 11))
						(1 (Print 22 12))
					)
					(curRoom setScript: endOfGameScript 0 1)
				)
			)
		)
	)
)

(instance endOfGameScript of Script
	(properties)
	
	(method (changeState)
		(if register
			(EgoDead 918 0 0 22 13)
		else
			(EgoDead 918 0 0 22 14)
		)
	)
)

(instance newRoomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(driver setCycle: EndLoop self)
			)
			(1
				(driver
					loop: 5
					setPri: (- (doorProp priority?) 1)
					setCycle: EndLoop self
				)
				(doorProp show: setCycle: EndLoop self)
			)
			(2 (= seconds 2))
			(3
				(curRoom drawPic: 101 setScript: messageLeavePearlScript)
			)
		)
	)
)

(instance messageLeavePearlScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose)
				(Print 22 15)
				(Print 22 16)
				(Print 22 17)
				(ego put: iEarring 3)
				(HandsOn)
				(curRoom newRoom: 23)
			)
		)
	)
)

(instance slidingDoor of Prop
	(properties
		y 171
		x 155
		view 22
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: stopUpd:)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			(
				(and
					(< (= temp0 (ego distanceTo: self)) 45)
					(not cel)
				)
				(self setCycle: EndLoop self)
			)
			((and (>= temp0 45) cel)
				(self setCycle: BegLoop self)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance plane of Actor
	(properties
		view 17
		loop 5
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: loop
			ignoreControl: cWHITE
			setScript: planeScript
			stopUpd:
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((IsOffScreen self))
			((Said 'look[<at][/airplane,airplane,jet]')
				(Print 22 18)
			)
		)
	)
)

(instance planeScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 20)))
			(1
				(plane posn: -5 7 setMotion: MoveTo 340 7 self)
			)
			(2
				(client stopUpd:)
				(= seconds (Random 20 40))
			)
			(3
				(self init: client)
			)
		)
	)
)

(instance alohaSignPic of PicView
	(properties
		y 189
		x 289
		view 22
	)
)

(instance limoPic of PicView
	(properties
		y 189
		x 34
		view 22
		loop 2
	)
)

(instance doorProp of Prop
	(properties
		y 157
		x 42
		view 22
		loop 3
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd: hide:)
	)
	
	(method (cue)
		(self hide: stopUpd:)
	)
)
