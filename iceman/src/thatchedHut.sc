;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
(use Main)
(use Intrface)
(use tahiti)
(use SoundSyncWave)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Sight)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	thatchedHut 0
)

(local
	bikini2Lady
	bikini3Lady
)
(procedure (localproc_0958 param1 param2)
	(ego
		loop: 5
		setCel:
			(switch (/ (+ 18 param2) 36)
				(3 4)
				(4 3)
				(5 2)
				(6 1)
				(7 0)
				(8 0)
				(else  2)
			)
		heading: param2
	)
)

(procedure (EgoSitting)
	(return (== (ego view?) 2))
)

(instance thatchedHut of Room
	(properties
		picture 2
		horizon 99
		north 9
		east 12
		south 16
		west 3
		picAngle 70
	)
	
	(method (init)
		(super init:)
		(if (== (tahiti script?) (ScriptID 300 1))
			(if (and (< 124 (ego y?)) (< (ego y?) 140))
				(ego y: 124)
			)
			(if (and (< 139 (ego y?)) (< (ego y?) 159))
				(ego y: 158)
			)
			(dinghyRamp init:)
			(addToPics add: noSinkyDinghy dinghyPost)
			(self setFeatures: dinghyMan noSinkyDinghy)
		)
		(addToPics add: chairPic eachElementDo: #init doit:)
		(self setFeatures: lobbyDoor chairPic setRegions: 301 300)
		(LoadMany VIEW 2 200 206 602 502 402)
		(ego init:)
		(= bikini2Lady (ScriptID 301 2))
		(= bikini3Lady (ScriptID 301 3))
		(switch prevRoomNum
			(north (ego posn: 133 105))
			(east (ego x: 315))
			(west
				(ego x: 5)
				(if (and (== (ego view?) 206) (> (ego y?) 115))
					(ego y: 115)
				)
				(if (< (ego y?) 100) (ego y: 100))
			)
			(south
				(ego y: 187)
				(if (== (ego onControl: 1) 1) (ego view: 200))
			)
			(else 
				(ego
					view: 2
					loop: 3
					cel: 0
					heading: 180
					posn: 227 152
					init:
					setScript: relaxScript
				)
				(User canControl: FALSE canInput: TRUE)
				(tahiti flags: (& (tahiti flags?) $bfff))
			)
		)
		(shirt init:)
		(hutDoor posn: 155 80 setLoop: 0 setPri: 3 init: stopUpd:)
		(wave init: globalSound setPri: 5)
		((ScriptID 301)
			notify: 0 wave 65 180 1 113 189 2 36 164 3 67 151 3 188 189 4
		)
		(bikini1Lady init:)
		(paperView init:)
		(User canInput: 1)
		(MenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(Said
					'look[<around,at][/room,beach,building,building,hotel]'
				)
				(Print 2 0 #time 10)
			)
			((Said '[/babe,man]>')
				(cond 
					((Said 'address')
						(Print 2 1)
					)
					((Said 'fuck')
						(Print 2 2)
					)
				)
			)
		)
	)
)

(instance bikini1Lady of Actor
	(properties
		y 85
		x 325
		yStep 1
		view 602
		signal (| ignrAct ignrHrz)
		illegalBits $0000
		xStep 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk setScript: bikini1Script)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((IsOffScreen self))
			((Said '[/brunette,babe[<brunette]]>')
				(cond 
					((== (ego view?) 2)
						(cond 
							((Said 'look[<at]')
								(Print 2 3)
							)
							((Said 'address')
								(Print 2 4)
							)
							((Said 'fuck')
								(Print 2 5)
							)
						)
					)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 2 6)
					)
					((Said 'address')
						(Print 2 4)
					)
					((Said 'fuck')
						(Print 2 5)
					)
				)
			)
		)
	)
)

(instance bikini1Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client posn: 330 84 xStep: 2 setLoop: -1 setPri: 4)
				(= seconds (Random 30 50))
			)
			(1
				(client setMotion: MoveTo 226 84 self)
			)
			(2
				(client setMotion: MoveTo 145 78 self)
			)
			(3
				(client setLoop: 3)
				(hutDoor startUpd: setCycle: EndLoop self)
			)
			(4
				(client xStep: 1 setMotion: MoveTo 152 76 self)
			)
			(5
				(client posn: 330 84 setPri: 2)
				(hutDoor setCycle: BegLoop self)
			)
			(6
				(hutDoor stopUpd:)
				(= seconds (Random 10 30))
			)
			(7 (self init: client))
		)
	)
)

(instance hutDoor of Prop
	(properties
		view 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 2 7)
					)
					((Said 'open,close,knock')
						(NotClose)
					)
				)
			)
		)
	)
)

(instance lobbyDoor of RFeature
	(properties
		y 65
		x 136
		nsTop 52
		nsLeft 130
		nsBottom 79
		nsRight 142
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 2 7)
					)
					((Said 'open,close,knock')
						(NotClose)
					)
				)
			)
		)
	)
)

(instance chairPic of RPicView
	(properties
		y 150
		x 233
		view 2
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said '(get<up),(stand[<up])')
				(if (EgoSitting)
					(if (!= (ego script?) standScript)
						(ego setScript: standScript)
					)
				else
					(Print 2 8)
				)
			)
			((Said '[/chair]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]>')
						(cond 
							((== (ego view?) 2)
								(if (Said '/*')
									(Print 2 9)
									(Print 2 10)
								)
							)
							((== (ego view?) 206)
								(Print 2 11)
								(Print 2 10)
								(event claimed: TRUE)
							)
							(else
								(Print 2 12)
								(Print 2 10)
								(event claimed: TRUE)
							)
						)
					)
					((Said 'sit>')
						(cond 
							((EgoSitting)
								(Print 2 13)
							)
							((GoToIfSaid self event (- x 6) (+ y 2) 'sit' 2 14))
							(else
								(ego setScript: sitScript)
							)
						)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
)

(instance paperView of View
	(properties
		y 153
		x 246
		z 12
		view 2
		loop 1
		cel 5
		priority 11
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/table,magazine,news,newspaper]>')
				(cond 
					((Said 'read,get,(pick<up)/magazine,news,newspaper')
						(ego setScript: readPaperS)
					)
					((TurnIfSaid self event 'look[<at,on]/*'))
					((Said 'look[<at,on]')
						(Print 2 15)
					)
				)
			)
		)
	)
)

(instance wave of SoundSyncWave
	(properties
		y 174
		x 35
		view 502
		cycleSpeed 2
	)
	
	(method (handleEvent)
		(if (Said 'look[<at][/wave]')
			(Print 2 16)
		)
	)
)

(instance noSinkyDinghy of RPicView
	(properties
		y 139
		x 4
		view 402
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/boat,dinghy]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 2 17)
					)
					((Said 'board,(get<on)')
						(Print 2 18)
					)
				)
			)
		)
	)
)

(instance dinghyPost of PicView
	(properties
		y 142
		x 25
		view 402
		cel 2
		priority 11
		signal ignrAct
	)
)

(instance dinghyRamp of View
	(properties
		y 153
		x 80
		view 402
		cel 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 9)
		(&= signal $feff)
	)
	
	(method (doit)
		(super doit:)
		(if (== (ego onControl: origin) cGREY)
			(HandsOff)
			(ego
				view: (if (OneOf (ego view?) 214 215) 200 else (ego view?))
				viewer: 0
				setScript: finallyOffThisStupidIsland
			)
			(|= signal staticView)
		)
	)
)

(instance dinghyMan of RFeature
	(properties
		y 114
		x 41
		nsTop 94
		nsLeft 34
		nsBottom 135
		nsRight 48
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/man,captain]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 2 19)
					)
					((Said 'address')
						(Print 2 20)
					)
				)
			)
		)
	)
)

(instance finallyOffThisStupidIsland of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(tahiti setScript: 0)
				(Print 2 21)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 62 142 self
				)
			)
			(1
				(ego setMotion: MoveTo 2 142 self)
			)
			(2
				(curRoom newRoom: 1)
			)
		)
	)
)

(instance shirt of View
	(properties
		view 2
		loop 1
		cel 3
	)
	
	(method (init)
		(self ignoreActors: TRUE posn: 217 150 10 setPri: 11)
		(super init:)
		(if (and (!= (ego view?) 206) (== curRoomNum 2))
			(self show:)
		else
			(self signal: (& signal $feff) hide:)
		)
	)
	
	(method (handleEvent event)
		(if (Said '[/shirt][/!*]>')
			(cond 
				((== (ego view?) 206)
					(cond 
						((Said 'look[<at]')
							(Print 2 22)
						)
						((Said '(get<!*),(wear[<!*]),(adjust<on)')
							(Print 2 23)
						)
						((TurnIfSaid self event 'look[<at]/*'))
						(
							(GoToIfSaid
								self
								event
								(- x 5)
								(- y 5)
								'(detach[<!*]),(get<off)'
								2
								14
							)
						)
						((Said '(detach[<!*]),(get<off)')
							(ego setScript: removeShirtScript)
						)
					)
				)
				((Said '(detach[<!*]),(get<off)')
					(Print 2 24)
				)
				((TurnIfSaid self event 'look[<at]/*'))
				((Said 'look[<at]')
					(Print 2 22)
				)
				((GoToIfSaid self event (- x 5) (- y 5) '/*' 2 14))
				((Said '(get[<!*]),(wear[<!*]),(adjust<on)')
					(ego setScript: wearShirtScript)
				)
			)
		)
	)
	
	(method (show)
		(if (== curRoomNum 2) (super show:))
	)
)

(instance relaxScript of Script
	
	(method (doit &tmp temp0 temp1)
		(if
			(and
				(= temp0
					(cond 
						((not (IsOffScreen bikini2Lady)) bikini2Lady)
						((not (IsOffScreen bikini3Lady)) bikini3Lady)
					)
				)
				(<
					90
					(= temp1
						(GetAngle
							(client x?)
							(client y?)
							(temp0 x?)
							(temp0 y?)
						)
					)
				)
				(< temp1 285)
			)
			(|= register $0001)
			(if (not script) (localproc_0958 temp0 temp1))
		else
			(if (& register $0002)
				(&= register $0000)
				(self cue:)
			)
			(&= register $fffc)
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 5 10))
				(client cycleSpeed: 2)
			)
			(1
				(client loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(2 (= seconds (Random 5 8)))
			(3 (client setCycle: BegLoop self))
			(4 (= seconds (Random 3 8)))
			(5 (self init: client))
		)
	)
	
	(method (cue param1)
		(cond 
			((& register $0001)
				(|= register $0002)
			)
			((& register $0004)
				(if (and argc param1)
					(&= register $fffb)
					(super cue:)
				)
			)
			(else (super cue:))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'get,wear,(adjust<on)/shirt')
				(ego setScript: wearShirtScript)
			)
		)
	)
)

(instance sitScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (ego view?) 206)
					(self setScript: removeShirtScript self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(ego
					setAvoider: Avoider
					setMotion: MoveTo (- (chairPic x?) 6) (+ (chairPic y?) 2) self
				)
			)
			(2
				(ego
					setAvoider: 0
					view: 2
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(HandsOn)
				(User canControl: FALSE)
				(ego posn: 227 152 heading: 180 setLoop: 3)
				(if (!= client readPaperS)
					(ego setScript: relaxScript)
				)
				(self dispose:)
			)
		)
	)
)

(instance standScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(ego
					view: 2
					setLoop: 2
					posn: 227 152
					setCel: 16
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(2
				(ego view: 200 setLoop: -1 setCycle: Walk cycleSpeed: 0)
				(if (not caller)
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance removeShirtScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider setMotion: MoveTo 212 145 self)
			)
			(1
				(ego
					view: 208
					setLoop: 0
					cel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(2
				(shirt posn: 217 150 10 show:)
				(ego view: 200 setCycle: Walk setAvoider: 0 setLoop: -1)
				(tahiti flags: (& (tahiti flags?) (~ fWearingShirt)))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance wearShirtScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (EgoSitting)
					(self setScript: standScript self)
				else
					(HandsOff)
					(= cycles 1)
				)
			)
			(1
				(ego view: 208 setLoop: 0 setCel: 0 setCycle: EndLoop self)
				(shirt hide:)
			)
			(2
				(HandsOn)
				(ego view: 206 setCycle: Walk setLoop: -1)
				(tahiti flags: (| (tahiti flags?) fWearingShirt))
				(self dispose:)
			)
		)
	)
)

(instance readPaperS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (EgoSitting))
					(self setScript: sitScript self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(ego loop: 3 setCycle: CycleTo 2 1 self)
			)
			(2
				(ego setCycle: EndLoop self)
				(paperView hide:)
			)
			(3
				(ego loop: 4 setCycle: EndLoop self)
			)
			(4
				(Print 2 25)
				(Print 2 26)
				(Print 2 27)
				(SolvePuzzle tahiti #pointFlag $0001 1)
				(= seconds 2)
			)
			(5
				(ego loop: 4 setCycle: CycleTo 2 -1 self)
			)
			(6
				(ego setCycle: BegLoop self)
				(paperView show:)
			)
			(7
				(ego loop: 3 setScript: relaxScript)
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
)
