;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include game.sh)
(use Main)
(use Intrface)
(use tahiti)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	hotelEntrance 0
)

(instance hotelEntrance of Room
	(properties
		picture 9
		horizon 128
		north 10
		east 12
		south 2
		west 3
	)
	
	(method (init)
		(super init:)
		(addToPics add: newsStand eachElementDo: #init doit:)
		(Load SOUND 42)
		(Load SOUND 36)
		(LoadMany VIEW 9 9 206 200 109 209)
		(self
			setRegions: 300 301
			setFeatures:
				hut
				newsStand
				flowerFeat
				westwindow
				((Clone westwindow)
					x: 145
					nsTop: 84
					nsBottom: 116
					nsLeft: 140
					nsRight: 151
					heading: 180
					name: {middleLeftWindow}
					yourself:
				)
				((Clone westwindow)
					x: 180
					nsTop: 84
					nsBottom: 119
					nsLeft: 170
					nsRight: 189
					heading: 180
					name: {middleRightWindow}
					yourself:
				)
				((Clone westwindow)
					x: 235
					nsTop: 100
					nsBottom: 129
					nsLeft: 219
					nsRight: 252
					heading: 180
					name: {eastLeftWindow}
					yourself:
				)
				((Clone westwindow)
					x: 282
					nsTop: 97
					nsBottom: 129
					nsLeft: 262
					nsRight: 302
					heading: 180
					name: {eastRightWindow}
					yourself:
				)
		)
		(ego init: observeControl: cYELLOW)
		(switch prevRoomNum
			(north (ego posn: 106 135))
			(else  (ego posn: 106 188))
		)
		(egoReflection init:)
		(lobbyDoor init:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]')
				(Print 9 0)
			)
		)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
		(ego ignoreControl: cYELLOW)
	)
)

(instance egoReflection of Prop
	(properties
		view 9
	)
	
	(method (init)
		(self ignoreActors: setPri: 8)
		(super init:)
	)
	
	(method (doit &tmp egoX egoLoop)
		(super doit:)
		(if (> 137 (ego y?)) (self show:) else (self hide:))
		(= egoX (ego x?))
		(= egoLoop (ego loop?))
		(self
			setLoop: (if (> egoLoop 3) (- egoLoop 4) else egoLoop)
			setCel: (ego cel?)
			posn: (if (< egoX 160) (+ egoX 3) else (- egoX 3)) (- (ego y?) 2)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/glass,reflection')
				(Print 9 1 #time 10)
			)
		)
	)
)

(instance lobbyDoor of Prop
	(properties
		y 132
		x 133
		heading 180
		view 209
		loop 4
	)
	
	(method (init)
		(super init:)
		(switch prevRoomNum
			((curRoom north?)
				(self
					setCel: (self lastCel:)
					ignoreActors:
					setCycle: BegLoop self
				)
				(theSound number: (SoundFX 42) loop: 1 play:)
			)
			(else 
				(self ignoreActors: stopUpd:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 9 2)
					)
					((Said 'close/*')
						(Print 9 3)
					)
					((Said 'knock/*')
						(Print 9 4)
					)
					(
						(GoToIfSaid self event
							(- (lobbyDoor x?) 14)
							(+ (lobbyDoor y?) 2)
							0 9 5
						)
					)
					((Said 'open/*')
						(if (!= (ego view?) 206)
							(Print 9 6)
						else
							(self setScript: openDoorScript self)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance openDoorScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 3 heading: 0)
				(lobbyDoor setCycle: EndLoop self)
				(theSound number: (SoundFX 36) loop: 1 play:)
			)
			(1
				(ego
					ignoreControl: cYELLOW
					setMotion: MoveTo (ego x?) (- (ego y?) 8)
					setAvoider: 0
				)
				(client setScript: 0)
			)
		)
	)
)

(instance newsStand of RPicView
	(properties
		y 132
		x 151
		heading 180
		view 109
		priority 9
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look/stand<magazine') (Print 9 7))
			(
			(Said '[/stand<newspaper,news,times,newspaper]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,in]')
						(if (> (ego distanceTo: self) 35)
							(Print 9 8)
						else
							(Print 9 9)
						)
					)
				)
			)
			((Said '[/newspaper,news,times,newspaper]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 9 10)
					)
					((GoToIfSaid self event self 25 0 9 5))
					((Said 'get,get')
						(Print 9 11)
					)
					((Said 'buy')
						(if (ego has: iChange)
							(Print 9 12)
							(ego setScript: readPaperScript)
						else
							(Print 9 13)
						)
					)
				)
			)
			((Said '[/money,change,cent,coin]>')
				(cond 
					((TurnIfSaid self event 'deposit,insert'))
					((GoToIfSaid self event self 25 'deposit,insert' 9 5))
					((Said 'deposit,insert')
						(if (ego has: iChange)
							(Print 9 12)
							(ego setScript: readPaperScript)
						else
							(Print 9 13)
						)
					)
				)
			)
		)
	)
)

(instance readPaperScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tahiti flags: (| (tahiti flags?) fReadNewspaper))
				(ego
					put: iChange curRoomNum
					setLoop: (if (== (ego view?) 206) 1 else 2)
					view: 109
					cycleSpeed: 1
					setCel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(1
				(Print 9 14 #at 65 120)
				(Print 9 15 #at 65 120)
				(ego setCycle: EndLoop self)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(Print 9 16 #at 65 120)
				(Print 9 17 #at 65 120)
				(Print 9 18 #at 65 120)
				(Print 9 19 #at 30 120 #width 260)
				(ego setCycle: EndLoop self)
			)
			(4
				(ego
					view: (if (== (ego loop?) 1) 206 else 200)
					cycleSpeed: 0
					setLoop: -1
					loop: 3
					setCycle: Walk
				)
				(= cycles 1)
			)
			(5
				(Print 9 20 #at 65 120)
				(theGame changeScore: 2)
				(ego setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance flowerFeat of RFeature
	(properties
		y 136
		x 26
		z 30
		nsTop 107
		nsBottom 123
		nsRight 68
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/planter,flower,box]>')
				(cond 
					((TurnIfSaid self event 'look[<at]'))
					((Said 'look[<at]')
						(Print 9 21)
					)
				)
			)
		)
	)
)

(instance westwindow of RFeature
	(properties
		y 106
		x 26
		z 10
		heading 180
		nsTop 84
		nsBottom 105
		nsRight 68
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look[<at,in]/*'))
					((Said 'look[<at,in]')
						(if (> (ego distanceTo: self) 20)
							(Print 9 22)
						else
							(Print 9 23)
						)
					)
				)
			)
		)
	)
)

(instance hut of RFeature
	(properties
		y 38
		x 159
		heading 180
		nsBottom 77
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]'))
					((Said 'look[<at]')
						(Print 9 2)
					)
				)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		priority 2
	)
)
