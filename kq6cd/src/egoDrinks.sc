;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	egoDrinks 0
	drinkMeScript 1
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
)
(instance egoDrinks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: killSound: 1)
				(theIconBar disable:)
				(messager say: 1 14 0 1 self 0)
			)
			(1
				(ego
					cycleSpeed: 10
					view: 935
					setPri: 14
					cel: 0
					setLoop: 0
					setCycle: End self
				)
				(localMusic number: 925 play: loop: 1)
			)
			(2 (= seconds 2))
			(3 (ego setCycle: Beg self))
			(4 (= cycles 15))
			(5
				(messager say: 1 14 0 2 self 0)
			)
			(6
				(client setScript: (ScriptID 87 1))
			)
		)
	)
)

(instance drinkMeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
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
				(DrawPic 981 dpOPEN_PIXELATION)
				(= cycles 2)
			)
			(1
				(messager say: 1 14 0 3 self 0)
			)
			(2
				(localMusic number: 926 play: self)
				(newEgo
					init:
					posn: 147 118
					setLoop: 1
					cel: 0
					setPri: 14
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(3
				(Palette palSET_FROM_RESOURCE 981)
			)
			(4
				(Palette palSET_FROM_RESOURCE 97)
				(if (== (localMusic prevSignal?) -1) (= cycles 1))
			)
			(5
				(if (== (localMusic prevSignal?) 10)
					(= state (- state 3))
				)
				(= cycles 1)
			)
			(6
				(Palette palSET_FROM_RESOURCE 981)
				(= cycles 1)
			)
			(7 (= seconds 4))
			(8
				(messager say: 1 14 0 4 self 0)
			)
			(9 (= seconds 5))
			(10
				(messager say: 1 14 0 5 self 0)
			)
			(11
				(localMusic number: 927 play: self)
			)
			(12
				(messager say: 1 14 0 6 self 0)
			)
			(13
				(newEgo cel: 0 setLoop: 2 setCycle: End self)
			)
			(14
				(newEgo cel: 0 setLoop: 3 setCycle: End self)
			)
			(15
				(cast
					eachElementDo: #dispose
					eachElementDo: #delete
					release:
					dispose:
				)
				(addToPics dispose:)
				(features delete: self dispose:)
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
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(ego reset: 2)
				(= cycles 3)
			)
			(16 (= cycles 3))
			(17
				(messager say: 1 14 0 7 self 0)
			)
			(18
				(messager say: 1 14 0 8 self 0)
			)
			(19
				(theIconBar enable:)
				(theGame handsOn: killSound: 0)
				(Bset 153)
				(localMusic stop: dispose:)
				(self dispose:)
				(LoadMany 0 87)
			)
		)
	)
)

(instance localMusic of Sound
	(properties)
)

(instance newEgo of Prop
	(properties
		view 935
	)
)
