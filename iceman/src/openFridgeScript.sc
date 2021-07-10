;;; Sierra Script 1.0 - (do not remove this comment)
(script# 362)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use Sight)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	openFridgeScript 0
	closeFridgeScript 1
)

(local
	local0
	local1
	theEgoX
	theEgoY
	local4
)
(procedure (OneItem)
	(Print 362 0)
)

(instance openFridgeScript of Script
	
	(method (doit)
		(super doit:)
		(if (and (< state 2) (CantBeSeen client ego 180))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop self)
			)
			(1
				(HandsOn)
				(tunisia fridgeIs: TRUE)
				(inFridge init:)
				(ketchup init:)
				(milk init:)
				(butter init:)
				(if (== (tunisia cheezIs?) TRUE)
					(cheez init:)
				)
			)
			(2 (client setCycle: BegLoop self))
			(3
				(client stopUpd:)
				(inFridge dispose:)
				(milk dispose:)
				(cheez dispose:)
				(butter dispose:)
				(ketchup dispose:)
				(= cycles 2)
			)
			(4
				(self dispose:)
				(DisposeScript 362)
				(tunisia fridgeIs: 0)
			)
		)
	)
)

(instance closeFridgeScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego script?)
					(ego cue:)
					(= local4 1)
				else
					(= cycles 1)
				)
			)
			(1
				(if (!= (ego heading?) 90)
					(ego heading: 90)
					((ego looper?) doit: ego (ego heading?) self)
				else
					(= cycles 1)
				)
			)
			(2 (client setCycle: BegLoop self))
			(3
				(client stopUpd:)
				(inFridge dispose:)
				(milk dispose:)
				(cheez dispose:)
				(butter dispose:)
				(ketchup dispose:)
				(= cycles 2)
			)
			(4
				(self dispose:)
				(DisposeScript 362)
				(tunisia fridgeIs: 0)
				(HandsOn)
			)
		)
	)
)

(instance milkActionScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (!= (ego heading?) 90)
					(ego heading: 90)
					((ego looper?) doit: ego (ego heading?))
					(= cycles 4)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: (if (== (= register (ego view?)) 84) 884 else 784)
					setLoop: 0
					setCel: 0
				)
				(if (== local0 2)
					(ego setCycle: EndLoop self)
				else
					(ego setCycle: CycleTo 4 1 self)
				)
				(milk hide:)
			)
			(2
				(if (== local0 2)
					(Print 362 1)
				else
					(Print 362 2)
				)
				(= seconds 2)
			)
			(3
				(if (== local0 2)
					(tunisia milkIs: 2)
				)
				(milk show:)
				(ego setCycle: BegLoop self)
			)
			(4
				(ego
					view: register
					loop: 0
					setPri: -1
					setLoop: -1
					cel: -1
					setCycle: Walk
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance cheezActionScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (!= (ego heading?) 90)
					(ego heading: 90)
					((ego looper?) doit: ego (ego heading?))
					(= cycles 4)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: (if (== (= register (ego view?)) 84) 884 else 784)
					setLoop: 1
					setCel: 0
				)
				(if (== local1 1)
					(ego setCycle: EndLoop self)
				else
					(ego setCycle: CycleTo 4 1 self)
				)
				(cheez hide:)
			)
			(2
				(if (== local1 1)
					(Print 362 3)
				else
					(Print 362 4)
				)
				(= seconds 2)
			)
			(3
				(if (== local1 1)
					(tunisia cheezIs: 2)
					(cheez dispose:)
				else
					(tunisia cheezIs: 1)
					(cheez show:)
				)
				(if (== local1 1)
					(= cycles 1)
				else
					(ego setCycle: BegLoop self)
				)
			)
			(4
				(ego
					view: register
					loop: 0
					setPri: -1
					setLoop: -1
					cel: -1
					setCycle: Walk
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getButterScript of Script
	
	(method (init)
		(super init: &rest)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (!= (ego heading?) 90)
					(ego heading: 90)
					((ego looper?) doit: ego (ego heading?))
					(= cycles 4)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: (if (== (= register (ego view?)) 84) 884 else 784)
					setLoop: 2
					setCel: 0
				)
				(ego setCycle: EndLoop self)
				(butter hide:)
			)
			(2
				(Print 362 5)
				(User canInput: TRUE)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego
					view: register
					setPri: -1
					loop: 0
					setLoop: -1
					cel: -1
					setCycle: Walk
				)
				(butter show:)
				(HandsOn)
				(if (and theEgoX theEgoY)
					(ego setMotion: MoveTo theEgoX theEgoY)
				)
				(if local4 (closeFridgeScript cue:))
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event &tmp egoX egoY)
		(super handleEvent: event)
		(= theEgoX 0)
		(= theEgoY 0)
		(switch (event type?)
			(direction
				(= egoX (ego x?))
				(= egoY (ego y?))
				(switch (event message?)
					(dirStop
						(= theEgoX egoX)
						(= theEgoY egoY)
					)
					(dirN
						(= theEgoX egoX)
						(= theEgoY (- egoY 1000))
					)
					(dirNE
						(= theEgoX (+ egoX 1000))
						(= theEgoY (- egoY 1000))
					)
					(dirE
						(= theEgoX (+ egoX 1000))
						(= theEgoY egoY)
					)
					(dirSE
						(= theEgoX (+ egoX 1000))
						(= theEgoY (+ egoY 1000))
					)
					(dirS
						(= theEgoX egoX)
						(= theEgoY (+ egoY 1000))
					)
					(dirSW
						(= theEgoX (- egoX 1000))
						(= theEgoY (+ egoY 1000))
					)
					(dirW
						(= theEgoX (- egoX 1000))
						(= theEgoY egoY)
					)
					(dirNW
						(= theEgoX (- egoX 1000))
						(= theEgoY (- egoY 1000))
					)
				)
				(self cue:)
			)
			(mouseDown
				(= theEgoX (event x?))
				(= theEgoY (event y?))
				(self cue:)
			)
			(saidEvent
				(cond 
					(
						(or
							(Said 'detach/lid,top')
							(Said 'open/butterdish,butter,dish,(dish<butter)')
						)
						(Print 362 6)
						(if (ego has: iNote)
							(Print 362 7)
						else
							(Print 362 8)
						)
					)
					((Said 'look/bag[<plastic]')
						(if (ego has: iNote)
							(event claimed: TRUE)
						else
							(Print 362 9)
						)
					)
					((Said 'look/note,newspaper,letter')
						(if (ego has: iNote)
							(event claimed: TRUE)
						else
							(Print 362 10)
						)
					)
					(
						(or
							(Said 'get,open/bag[<plastic]')
							(Said 'get,detach/newspaper,note,letter')
						)
						(if (ego has: iNote)
							(event claimed: TRUE)
						else
							(Print 362 11)
							(Print 362 12)
							(ego get: iNote)
							(self cue:)
						)
					)
				)
			)
			(else
				(event claimed: FALSE)
			)
		)
	)
)

(instance inFridge of View
	(properties
		y 76
		x 276
		view 284
		loop 2
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 10)
	)
)

(instance milk of View
	(properties
		y 38
		x 288
		view 284
		loop 2
		cel 2
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(self setPri: 11)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/milk]>')
				(cond 
					((Said 'look[<at]')
						(if (== (tunisia milkIs?) 2)
							(Print 362 13)
						else
							(Print 362 14)
						)
					)
					((Said 'get')
						(cond 
							((ego script?) (OneItem))
							((== (tunisia milkIs?) 2) (Print 362 15))
							(else (= local0 0) (ego setScript: milkActionScript))
						)
					)
					((Said 'drink')
						(cond 
							((ego script?) (OneItem))
							((== (tunisia milkIs?) 2) (Print 362 15))
							(else (= local0 2) (ego setScript: milkActionScript))
						)
					)
					((Said 'smell')
						(if (== (tunisia milkIs?) 2)
							(Print 362 15)
						else
							(Print 362 16)
						)
					)
				)
			)
		)
	)
)

(instance cheez of View
	(properties
		y 53
		x 288
		view 284
		loop 2
		cel 3
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(self setPri: 11)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/cheese]>')
				(cond 
					((Said 'look[<at]')
						(Print 362 17)
					)
					((Said 'get')
						(if (ego script?)
							(OneItem)
						else
							(= local1 0)
							(ego setScript: cheezActionScript)
						)
					)
					((Said 'eat')
						(if (ego script?)
							(OneItem)
						else
							(= local1 1)
							(ego setScript: cheezActionScript)
						)
					)
					((Said 'smell')
						(if (== (tunisia cheezIs?) 2)
							(Print 362 18)
						else
							(Print 362 16)
						)
					)
				)
			)
		)
	)
)

(instance butter of View
	(properties
		y 23
		x 285
		view 284
		loop 2
		cel 4
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(self setPri: 11)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/(dish<butter),butter,butterdish]>')
				(cond 
					((Said 'examine,look[<at,in]') (Print 362 19))
					((Said 'get')
						(if (ego script?)
							(Print 362 20)
						else
							(ego setScript: getButterScript)
						)
					)
					((Said 'adjust,drop') (if (ego script?) (ego cue:) else (Print 362 21)))
					((Said 'eat') (Print 362 22))
					((Said 'smell') (Print 362 16))
				)
			)
		)
	)
)

(instance ketchup of View
	(properties
		y 51
		x 291
		view 284
		loop 2
		cel 5
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(self setPri: 11)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/catsup]>')
				(cond 
					((Said 'look[<at]') (Print 362 23))
					((Said 'get') (if (ego script?) (OneItem) else (Print 362 24)))
					((Said 'eat,drink') (Print 362 22))
					((Said 'open') (Print 362 25))
					((Said 'smell') (Print 362 26))
				)
			)
		)
	)
)
