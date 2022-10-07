;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include sci.sh)
(use Main)
(use Kq6Talker)
(use Kq6Procs)
(use Feature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	beginScript 0
	riddleBookScript 1
)

(local
	theCast
	theFeatures
	theAddToPics
	theMouseDownHandler
	theKeyDownHandler
	theDirectionHandler
	theWalkHandler
	curRoomObstacles
	local8
	theMessager
)
(instance beginScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable: 6)
				(if (not (Btst 129))
					(Bset 129)
					(theGame givePoints: 1)
				)
				(messager say: 1 27 0 1 self 0)
			)
			(1 (= seconds 2))
			(2
				(ego
					normal: 0
					view: 903
					cel: 0
					setLoop: 2
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(3
				(ego cel: 0 setLoop: 0 setCycle: EndLoop self)
			)
			(4
				(ego setLoop: 1 setCycle: Forward)
				(= seconds 4)
			)
			(5
				(client setScript: (ScriptID 90 1))
			)
		)
	)
)

(instance riddleBookScript of Script
	(properties)
	
	(method (init)
		(= theMessager messager)
		(= messager rBookMessager)
		(= theCast cast)
		(= theFeatures features)
		(= theAddToPics addToPics)
		(= theMouseDownHandler mouseDownHandler)
		(= theKeyDownHandler keyDownHandler)
		(= theDirectionHandler directionHandler)
		(= theWalkHandler walkHandler)
		(= curRoomObstacles (curRoom obstacles?))
		(curRoom obstacles: ((List new:) add: yourself:))
		((= cast (EventHandler new:)) name: {newCast} add:)
		((= features (EventHandler new:))
			name: {newFeatures}
			add: self
		)
		((= addToPics (EventHandler new:)) name: {newATPs} add:)
		((= mouseDownHandler (EventHandler new:))
			name: {newMH}
			add: self
		)
		((= keyDownHandler (EventHandler new:))
			name: {newKH}
			add: self
		)
		((= directionHandler (EventHandler new:))
			name: {newDH}
			add: self
		)
		((= walkHandler (EventHandler new:)) name: {newWH} add:)
		(if register (inventory hide:) (= register 0))
		(DrawPic 98)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(background init:)
				(bookView init: stopUpd:)
				(= seconds 2)
			)
			(1
				(messager say: 1 27 0 2 self 0)
			)
			(2
				(messager say: 1 27 0 3 self 0)
			)
			(3
				(theGame handsOn:)
				(features delete: self)
				(User controls: 0)
				(theIconBar disable: 0 3 4 5)
			)
			(4
				(theGame handsOff:)
				(if modelessDialog (modelessDialog dispose:))
				(theGame setCursor: waitCursor)
				(cast
					eachElementDo: #dispose
					eachElementDo: #delete
					release:
					dispose:
				)
				(addToPics dispose:)
				(features delete: background self dispose:)
				(mouseDownHandler delete: self dispose:)
				(keyDownHandler delete: self dispose:)
				(directionHandler delete: self dispose:)
				(walkHandler delete: self dispose:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: curRoomObstacles)
				(= cast theCast)
				(= features theFeatures)
				(= mouseDownHandler theMouseDownHandler)
				(= keyDownHandler theKeyDownHandler)
				(= directionHandler theDirectionHandler)
				(= walkHandler theWalkHandler)
				(= addToPics theAddToPics)
				(UnLoad 128 904)
				(DrawPic (curRoom picture?) dpOPEN_NO_TRANSITION)
				(if addToPics (addToPics doit:))
				(= seconds 2)
			)
			(5
				(ego
					setLoop: 2
					cycleSpeed: 10
					lastCel:
					setCycle: BegLoop self
				)
			)
			(6
				(ego reset: 2)
				(theIconBar enable: 6)
				(theGame handsOn:)
				(self dispose:)
				(= messager theMessager)
				(DisposeScript 90)
			)
		)
	)
)

(instance background of Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb)
		(riddleBookScript cue:)
	)
)

(instance bookView of Prop
	(properties
		x 149
		y 86
		view 904
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(riddleBookScript
				state: (- (riddleBookScript state?) 3)
				cue:
			)
		else
			(background doVerb: theVerb)
		)
	)
)

(instance rBookMessager of Kq6Messager
	(properties)
	
	(method (findTalker param1 &tmp theLocalNarrator)
		(if (== param1 99)
			(= theLocalNarrator localNarrator)
			(return)
		else
			(super findTalker: param1)
		)
	)
)

(instance localNarrator of Kq6Narrator
	(properties
		y 135
	)
	
	(method (init)
		(= talkWidth 280)
		(super init: &rest)
	)
)
