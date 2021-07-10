;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include sci.sh)
(use Main)
(use Intrface)
(use tahiti)
(use n316)
(use InitAllFeatures)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Sight)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	inEgosHut 0
)

(local
	[local0 20]
)
(procedure (localproc_0fc6)
	(return
		(if
			(and
				(closetProp cel?)
				(not (CantBeSeen closetView ego 270 50))
			)
			(closetView show:)
			(return 1)
		else
			(closetView hide:)
			(return 0)
		)
	)
)

(instance inEgosHut of Rm
	(properties
		picture 6
		north 8
		south 5
		vanishingX 170
		vanishingY -20
	)
	
	(method (init)
		(super init:)
		(addToPics
			add: fishPic floorPic
			eachElementDo: #init
			doit:
		)
		(LoadMany 128 200 206 106 6)
		(Load rsSOUND 42)
		(Load rsSOUND 36)
		(drawerView init:)
		(closetView init:)
		(phone init:)
		(drawerProp init:)
		(closetProp init:)
		(ego init:)
		(switch prevRoomNum
			(8
				(ego setScript: walkUpLadderScript)
			)
			(else 
				(if (> (ego y?) 170)
					(ego posn: 77 68 loop: 0)
				else
					(ego posn: (hutDoor x?) (- (hutDoor y?) 9) loop: 3)
				)
			)
		)
		(self
			setRegions: 300
			setFeatures:
				hutDoor
				fishPic
				floorPic
				flowersOnTable
				bedFeature
				backCouchF
				leftCouch
				((Clone bedFeature)
					x: 36
					y: 128
					nsTop: 114
					nsBottom: 142
					nsLeft: 28
					nsRight: 44
					yourself:
				)
				((Clone leftCouch)
					x: 215
					y: 98
					nsLeft: 219
					nsTop: 82
					nsRight: 244
					nsBottom: 114
					heading: 270
					name: {rightCouch} species
				)
				lampFeature
				windowFeature
				rugFeature
				floorFeature
				((Clone floorFeature) x: 105 y: 209 yourself:)
				((Clone windowFeature)
					x: 220
					y: 40
					nsTop: 15
					nsBottom: 68
					nsLeft: 180
					nsRight: 260
					yourself:
				)
				oceanFeat
				((Clone rugFeature)
					x: 160
					y: 95
					nsTop: 80
					nsBottom: 110
					nsLeft: 136
					nsRight: 186
					yourself:
				)
		)
		(InitAllFeatures)
		(RemoveInvItems curRoomNum 3 2 4)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 1) $4020) (self newRoom: 5))
	)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look>') (if (Said '[<around][/room][/building]') (Print 6 0)))
			((Said 'rest') (Print 6 1))
			((Said 'jump,swim,dive,enter,(go<in)[/water]')
				(cond 
					((== (ego view?) 206) (Print 6 2))
					((== (ego onControl: 1) 16) (ego setScript: fallInWaterScript))
					(else (Print 6 3))
				)
			)
			((Said 'read/book')
				(if (ego has: 3)
					(Print 6 4 #icon 300 1 0)
				else
					(event claimed: 0)
				)
			)
			((Said 'turn<on,off/light') (DontNeedTo))
		)
	)
	
	(method (cue)
		(super cue:)
		(self newRoom: 8)
	)
)

(instance fallInWaterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoid setMotion: MoveTo 158 67 self)
			)
			(1
				(ego heading: 0)
				((ego looper?) doit: ego 0)
				(= cycles 5)
			)
			(2
				((ego looper?) dispose:)
				(ego
					setPri: 0
					yStep: 10
					illegalBits: 0
					looper: 0
					setLoop:
					setMotion: MoveTo (ego x?) 107 self
				)
			)
			(3
				(HandsOn)
				(ego
					yStep: 2
					setPri: -1
					setLoop: -1
					observeControl: -32768
				)
				(curRoom newRoom: 8)
			)
		)
	)
)

(instance walkUpLadderScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 200
					setLoop: 2
					posn: 158 107
					setPri: 0
					moveSpeed: 1
					setMotion: MoveTo 158 67 self
				)
			)
			(1
				(HandsOn)
				(ego heading: 180 setPri: -1 moveSpeed: 0 setLoop: -1)
			)
		)
	)
)

(instance hutDoor of Feature
	(properties
		y 175
		x 122
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door[<building]][/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 5))
					((Said 'knock') (Print 6 6))
					((GoToIfSaid self event x (- y 5) 0 6 7))
					((Said 'open') (HandsOff) (curRoom setScript: egoOpenDoorScript))
				)
			)
		)
	)
)

(instance phone of Prop
	(properties
		y 155
		x 29
		z 7
		view 106
		loop 1
		priority 11
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/call,braxton]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 8))
					(
						(or
							(Said '(get[<!*]),call,dial,(pick<up)')
							(Said 'call<use')
							(Said 'make/call<call')
						)
						(cond 
							((== (ego onControl: 1) 16) (Print 6 9))
							((User controls?)
								(HandsOff)
								(ego
									setAvoider: Avoid
									setMotion: MoveTo (+ 25 x) (+ 7 y) self
								)
							)
							(else (Print 6 7))
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(ego setScript: phoneScript)
	)
)

(instance phoneScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client
					view: 106
					loop: (if (== (ego view?) 200) 0 else 4)
					setCycle: End self
				)
				(phone cel: 1 forceUpd:)
			)
			(1
				(Print 6 10 #edit @local0 18)
				(= seconds 3)
			)
			(2
				(cond 
					((not (StrCmp @local0 {})) (self cue:))
					((not (StrCmp @local0 {1-202-555-2729}))
						(if
							(and
								(& (tahiti flags?) $0100)
								(not (& (tahiti flags?) $0080))
							)
							(Print 6 11 #time 10)
							(Print 6 12)
							(User canInput: 1)
							(= register 0)
							(= seconds 10)
						else
							(Print 6 13 #time 10)
							(ego setScript: putDownPhoneScript)
						)
					)
					((not (StrCmp @local0 {555-8000}))
						(if
							(and
								(& (tahiti flags?) $0100)
								(& (tahiti flags?) $0080)
								(not (& (tahiti flags?) $0200))
							)
							(Print 6 14)
							(User canInput: 1)
							(= register 1)
							(= seconds 10)
						else
							(Print 6 15)
							(ego setScript: putDownPhoneScript)
						)
					)
					(else (Print 6 16 #time 10) (ego setScript: putDownPhoneScript))
				)
			)
			(3
				(Print 6 17)
				(ego setScript: putDownPhoneScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(register
				(if
					(or
						(Said 'address[/man,clerk]')
						(Said '//boat,transport<for,about')
						(Said '/hello')
						(Said
							'/lieutenant,commander,john,westland,(westland<john)'
						)
						(Said '/boat,transport,dinghy')
						(Said 'boat,transport,dinghy/')
					)
					(Print 6 18)
					(Print 6 19)
					(tahiti setScript: (ScriptID 300 1))
					(tahiti flags: (| (tahiti flags?) $0200))
					(theGame changeScore: 2)
				else
					(Print 6 20)
					(event claimed: 1)
				)
				(Print 6 17)
				(ego setScript: putDownPhoneScript)
			)
			(else
				(if
					(or
						(Said 'address[/man,general,braxton]')
						(Said '//message<for,about')
						(Said '/hello')
						(Said
							'/lieutenant,commander,john,westland,(westland<john)'
						)
					)
					(Print 6 21)
					(if ((tahiti script?) state?)
						(Print 6 22)
						(Print 6 23)
					else
						(Print 6 24)
						(Print 6 25)
						(theGame changeScore: 2)
					)
					(Print 6 26)
					(Print 6 27)
					(Print 6 28)
					(tahiti flags: (| (tahiti flags?) $0080))
				else
					(Print 6 20)
					(event claimed: 1)
				)
				(Print 6 17)
				(ego setScript: putDownPhoneScript)
			)
		)
	)
)

(instance putDownPhoneScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCycle: Beg self)
				(phone cel: 0 forceUpd:)
			)
			(1
				(ego
					view: (if (== (ego loop?) 0) 200 else 206)
					loop: 1
					setLoop: -1
					setCycle: Walk
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance drawerProp of Prop
	(properties
		y 168
		x 33
		z 7
		view 6
	)
	
	(method (init)
		(super init:)
		(if (& (tahiti flags?) $0020) (= cel (self lastCel:)))
		(self setPri: 12 stopUpd: ignoreActors: 1)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(Said '[/dresser,drawer,dresser,lamp,light,flower]>')
				(cond 
					((and (== (ego onControl: 1) 16) (Said '/*')) (Print 6 9))
					((TurnIfSaid self event 'look[<at,in,on]/*'))
					(
						(or
							(Said 'look[<on]/dresser,drawer,dresser')
							(Said 'look/lamp,light,flower')
						)
						(Print 6 29)
					)
					((Said 'look[<at,in]')
						(if (not cel)
							(Print 6 30)
						else
							(drawerView show:)
							(cond 
								((CantBeSeen self ego 180 50) (Print 6 31))
								(
									(and
										(IsInvItemInRoom curRoomNum 2)
										(IsInvItemInRoom curRoomNum 4)
									)
									(Print 6 32)
								)
								((IsInvItemInRoom curRoomNum 2) (Print 6 33))
								((IsInvItemInRoom curRoomNum 4) (Print 6 34))
								(else (Print 6 35))
							)
						)
					)
					(
					(GoToIfSaid self event self 20 'open,close,examine' 6 7))
					((Said 'open')
						(drawerView show:)
						(if cel
							(Print 6 36)
						else
							(tahiti flags: (| (tahiti flags?) $0020))
							(self setCycle: End self)
						)
					)
					((Said 'close')
						(if (not cel)
							(Print 6 37)
						else
							(Ok)
							(drawerView hide:)
							(tahiti flags: (& (tahiti flags?) $ffdf))
							(self setCycle: Beg self)
						)
					)
					((Said 'examine') (Print 6 38))
				)
			)
			(
			(Said 'adjust,return,drop[/id,(card[<id]),change,money]>')
				(cond 
					((TurnIfSaid self event 'adjust,return,drop/*'))
					((GoToIfSaid self event (+ 20 x) y 0 6 7))
					((not cel) (Print 6 39) (event claimed: 1) (return))
					((Said '/change,money')
						(if (ego has: 4)
							(if (not (& (drawerView signal?) $0080))
								(Change show:)
							)
							(Print 6 40)
							(ego put: 4 curRoomNum)
						else
							(DontHave)
						)
					)
					((ego has: 2)
						(event claimed: 1)
						(if (not (& (drawerView signal?) $0080))
							(Wallet show:)
						)
						(Print 6 41)
						(ego put: 2 curRoomNum)
					)
					(else (DontHave))
				)
			)
		)
	)
	
	(method (cue)
		(if cel (drawerView show:))
		(self stopUpd:)
	)
)

(instance drawerView of View
	(properties
		y 155
		x 35
		z 110
		view 6
		loop 1
	)
	
	(method (init)
		(super init:)
		(Change init:)
		(Wallet init:)
		(self setPri: 14 hide:)
	)
	
	(method (doit)
		(super doit:)
		(if (CantBeSeen self ego 180 50) (self hide:))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MousedOn self event)
				(if
					(not
						(if (IsInvItemInRoom curRoomNum 4)
						else
							(IsInvItemInRoom curRoomNum 2)
						)
					)
					(Print 6 42)
					(event claimed: 1)
				)
			)
		)
	)
	
	(method (hide)
		(super hide:)
		(= signal (| signal $0100))
		(Wallet hide:)
		(Change hide:)
	)
	
	(method (show)
		(super show:)
		(= signal (& signal $feff))
		(if (IsInvItemInRoom curRoomNum 4) (Change show:))
		(if (IsInvItemInRoom curRoomNum 2) (Wallet show:))
	)
)

(instance closetProp of Prop
	(properties
		y 161
		x 293
		view 6
		loop 7
		priority 11
		signal $4011
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/closet,(door<closet)]>')
				(cond 
					((TurnIfSaid self event 'look[<at,in]/*'))
					((Said 'look[<at,in]') (if (localproc_0fc6) (Print 6 43) else (Print 6 44)))
					((GoToIfSaid self event self 10 0 6 7))
					((Said 'open')
						(if (localproc_0fc6)
							(Print 6 36)
						else
							(self setCycle: End self)
						)
					)
					((Said 'close')
						(if cel
							(self setCycle: Beg self)
							(closetView hide:)
						else
							(Print 6 37)
						)
					)
				)
			)
			((Said 'look[<at]/coat,coat,coat,clothes,pants')
				(if (not (localproc_0fc6))
					(Print 6 45)
				else
					(event claimed: 0)
				)
			)
		)
	)
	
	(method (cue)
		(localproc_0fc6)
		(self stopUpd:)
	)
)

(instance closetView of View
	(properties
		y 155
		x 313
		z 80
		view 6
		loop 6
		cel 1
		priority 15
		signal $4010
	)
	
	(method (init)
		(super init:)
		(self hide:)
	)
	
	(method (doit)
		(super doit:)
		(localproc_0fc6)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((& signal $0008))
			(
				(or
					(Said '(get[<!*]),wear,(adjust<on)/coat,clothes,uniform')
					(Said 'change/clothes')
					(Said 'dress')
				)
				(Print 6 46)
			)
			((Said 'pack/bag,coat,clothes,uniform,briefcase') (DontNeedTo))
			((Said 'examine,look[<at,in]>')
				(cond 
					((Said '/coat,coat,coat,clothes') (Print 6 47))
					((Said '/pocket[/coat,coat]')
						(if (IsInvItemInRoom curRoomNum 3)
							(Print 6 48)
						else
							(Print 6 49)
						)
					)
					((Said '[/closet]') (Print 6 50))
				)
			)
			((Said '(get[<!*])/book[<call,black,address]')
				(if (IsInvItemInRoom curRoomNum 3)
					(Ok)
					(ego get: 3)
				else
					(AlreadyTook)
				)
			)
			(
			(Said 'return,drop,adjust/book[<call,black,address]')
				(if (ego has: 3)
					(Ok)
					(ego put: 3 curRoomNum)
				else
					(DontHave)
				)
			)
		)
	)
	
	(method (hide)
		(super hide:)
		(= signal (| signal $0100))
	)
	
	(method (show)
		(super show:)
		(= signal (& signal $feff))
	)
)

(instance Wallet of Prop
	(properties
		y 145
		x 25
		z 120
		view 6
		loop 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(self setPri: 15 posn: 25 145)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/id,card]')
				(if
					(and
						(IsInvItemInRoom curRoomNum 2)
						(& (tahiti flags?) $0020)
					)
					(Print 6 51)
				else
					(event claimed: 0)
				)
			)
			(
			(or (Said '(get[<!*])/id,card') (MousedOn self event))
				(cond 
					((not (& (tahiti flags?) $0020)) (event claimed: 0))
					((IsInvItemInRoom ego 2)
						(if (Said '(get[<!*])/id,card')
							(Print 6 52)
						else
							(Print 6 53)
						)
					)
					((not (User canControl:)) (Print 6 54))
					((> (ego distanceTo: self) 40) (ego setAvoider: Avoid setMotion: MoveTo 56 165 self))
					((IsInvItemInRoom curRoomNum 2) (self cue:))
				)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(Ok)
		(ego get: 2)
		(self hide:)
	)
)

(instance Change of Prop
	(properties
		y 145
		x 42
		z 120
		view 6
		loop 1
		cel 2
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(self setPri: 15 posn: 42 145)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/change,coin,money]')
				(if
					(and
						(IsInvItemInRoom curRoomNum 4)
						(& (tahiti flags?) $0020)
					)
					(Print 6 55)
				else
					(event claimed: 0)
				)
			)
			(
				(or
					(Said 'get/change,coin,money')
					(MousedOn self event)
				)
				(cond 
					((not (& (tahiti flags?) $0020)) (event claimed: 0))
					((IsInvItemInRoom ego 4)
						(if (Said 'get/change,coin,money')
							(Print 6 52)
						else
							(Print 6 53)
						)
					)
					((not (User canControl:)) (Print 6 54))
					((> (ego distanceTo: self) 40) (ego setAvoider: Avoid setMotion: MoveTo 56 165 self))
					((IsInvItemInRoom curRoomNum 4) (self cue:))
				)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self hide: stopUpd:)
		(Print 6 56 #time 10)
		(ego get: 4)
	)
)

(instance fishPic of RPicView
	(properties
		y 120
		x 34
		view 6
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/painting,wall]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 57))
					((Said 'move/*') (DontNeedTo))
				)
			)
		)
	)
)

(instance floorPic of RPicView
	(properties
		y 109
		x 105
		view 6
		loop 4
		cel 1
		priority 6
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/floor,floor]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,down,through]') (if (not (Random 0 5)) (Print 6 58) else (Print 6 59)))
				)
			)
		)
	)
)

(instance flowersOnTable of RFeature
	(properties
		y 62
		x 119
		z 10
		heading 180
		nsTop 50
		nsLeft 108
		nsBottom 75
		nsRight 131
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/table,flower]>')
				(cond 
					((and (< (ego y?) y) (Said '/!*>')))
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 60))
					((GoToIfSaid self event x (+ 25 y) 0 6 7))
					((Said 'smell') (Print 6 61))
				)
			)
		)
	)
)

(instance bedFeature of RFeature
	(properties
		y 140
		x 80
		heading 135
		nsTop 116
		nsLeft 44
		nsBottom 155
		nsRight 115
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bed]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 62))
					((Said 'look<below') (SeeNothing))
					((Said 'smell') (ego setScript: egoSitScript 0 1))
					((Said 'sit') (ego setScript: egoSitScript 0 8))
				)
			)
		)
	)
)

(instance leftCouch of RFeature
	(properties
		y 98
		x 100
		heading 90
		nsTop 74
		nsLeft 66
		nsBottom 111
		nsRight 85
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/couch]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 63))
					((GoToIfSaid self event x y 0 6 7))
					((Said 'sit') (ego setScript: egoSitScript 0 (if (== x 215) 2 else 4)))
					((Said 'smell') (Print 6 64))
					((Said 'feel') (Print 6 65))
				)
			)
		)
	)
)

(instance backCouchF of RFeature
	(properties
		y 74
		x 213
		heading 180
		nsTop 67
		nsLeft 183
		nsBottom 81
		nsRight 244
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/couch]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 63))
				)
			)
		)
	)
)

(instance egoSitScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoid)
				(switch register
					(2
						(ego setMotion: MoveTo 215 98 self)
					)
					(4
						(ego setMotion: MoveTo 98 98 self)
					)
					(8
						(ego setMotion: MoveTo 129 140 self)
					)
					(1
						(ego setMotion: MoveTo 129 140 self)
					)
				)
			)
			(1
				(ego setAvoider: 0 illegalBits: 0)
				(switch register
					(2
						(ego setMotion: MoveTo 220 98 self)
					)
					(4
						(ego setMotion: MoveTo 92 98 self)
					)
					(8
						(ego setMotion: MoveTo 123 140 self)
					)
					(1
						(switch (Random 1 3)
							(1 (Print 6 66))
							(2 (Print 6 67))
							(3 (Print 6 68))
						)
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(2
				(if (== register 2)
					(= temp0 (if (== (ego view?) 200) 3 else 6))
					(ego heading: 270)
				else
					(= temp0 (if (== (ego view?) 200) 2 else 5))
					(ego heading: 90)
				)
				(ego view: 106 setLoop: temp0 cel: 0 setCycle: End self)
			)
			(3 (User canInput: 1))
			(4 (ego setCycle: Beg self))
			(5
				(ego
					view: (if (or (== (ego loop?) 2) (== (ego loop?) 3))
						200
					else
						206
					)
					setCycle: Walk
				)
				(switch register
					(2
						(ego setLoop: 1 setMotion: MoveTo 215 98 self)
					)
					(4
						(ego setLoop: 0 setMotion: MoveTo 98 98 self)
					)
					(8
						(ego setLoop: 0 setMotion: MoveTo 129 140 self)
					)
				)
			)
			(6
				(ego setLoop: -1 illegalBits: -32768 setScript: 0)
				(HandsOn)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit') (Print 6 69))
			((Said 'stand') (self cue:))
		)
	)
)

(instance rugFeature of RFeature
	(properties
		y 140
		x 240
		nsTop 120
		nsLeft 210
		nsBottom 160
		nsRight 270
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/carpet,carpet]>')
				(cond 
					((TurnIfSaid self event 'look[<at,below]/*'))
					((Said 'look[<at,below]') (Print 6 70))
				)
			)
		)
	)
)

(instance windowFeature of RFeature
	(properties
		y 50
		x 100
		nsTop 15
		nsLeft 60
		nsBottom 68
		nsRight 135
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 71))
				)
			)
		)
	)
)

(instance lampFeature of RFeature
	(properties
		y 150
		x 20
		z 20
		nsTop 115
		nsLeft 10
		nsBottom 140
		nsRight 30
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/lamp]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 72))
				)
			)
		)
	)
)

(instance oceanFeat of RFeature
	(properties
		y 55
		x 160
		nsTop 15
		nsLeft 60
		nsBottom 68
		nsRight 260
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bay,water]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 6 73))
				)
			)
		)
	)
)

(instance floorFeature of Feature
	(properties
		y 109
		x 200
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/floor,floor]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,down]') (Print 6 74))
				)
			)
		)
	)
)

(instance egoOpenDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theSound number: (SoundFX 36) loop: 1 play: self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance theSound of Sound
	(properties
		priority 2
	)
)
