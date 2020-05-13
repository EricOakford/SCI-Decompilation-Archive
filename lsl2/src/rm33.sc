;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Extra)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm33 0
)

(enum ;room states
	notThisTime	
	firstMama
	heresMama
	mamaComingLater
)

(local
	mamaInRoom
	aPorthole
	mama
	aCloset
	aDrawer
	drawerIsOpen
	closetIsOpen
	roomState
)
(instance rm33 of Room
	(properties
		picture 33
	)
	
	(method (init)
		(Load VIEW 100)
		(Load VIEW 306)
		(Load VIEW 307)
		(Load VIEW 308)
		(Load VIEW 305)
		(Load VIEW 313)
		(Load SCRIPT JUMP)
		(super init:)
		((View new:)
			view: 305
			loop: 5
			cel: 0
			posn: 172 77
			setPri: 3
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 305
			loop: 0
			cel: 0
			posn: 222 86
			setPri: 5
			ignoreActors:
			addToPic:
		)
		(aBigMama
			view: 313
			posn: 273 1058
			setPri: 15
			setCycle: Forward
			init:
		)
		((= aPorthole (Prop new:))
			view: 305
			loop: 1
			setPri: 4
			posn: 172 74
			setCycle: Forward
			cycleSpeed: 10
			ignoreActors:
			isExtra: 1
			init:
		)
		(NormalEgo)
		(ego posn: 109 112 loop: 0 init:)
		(self setRegions: SHIP setScript: rm33Script)
		(if (== currentEgoView 100)
			(cond 
				((== 1 (++ metMama))
					(= mamaInRoom 1)
					(= roomState firstMama)
					((= mama (Extra new:))
						view: 305
						setLoop: 4
						setPri: 7
						posn: 201 110
						cycleSpeed: 1
						minPause: 20
						maxPause: 50
						init:
					)
					(rm33Script changeState: 2)
				)
				((> 3 (Random 1 5)) (= roomState notThisTime))
				((> 3 (Random 1 5))
					(= roomState 2)
					((= mama (Extra new:))
						view: 305
						setLoop: 4
						setPri: 7
						posn: 201 110
						cycleSpeed: 1
						minPause: 20
						maxPause: 50
						init:
					)
					(rm33Script changeState: 14)
				)
				(else
					(= roomState mamaComingLater)
					((= mama (Actor new:))
						view: 306
						loop: 3
						posn: 154 125
						illegalBits: 0
						ignoreActors:
						setCycle: Walk
						init:
						hide:
					)
					(rm33Script changeState: 5)
				)
			)
		)
		((= aCloset (Door new:))
			view: 305
			loop: 3
			setPri: 6
			posn: 137 96
			doorCtrl: 16
			doorBlock: 0
			roomCtrl: 0
			init:
		)
		((= aDrawer (Door new:))
			view: 305
			setLoop: 2
			setPri: 5
			posn: 161 88
			doorCtrl: 32
			doorBlock: 0
			roomCtrl: 0
			init:
		)
		(if debugging
			(RedrawCast)
			(switch roomState
				(firstMama (Print 33 0 #at -1 20))
				(heresMama (Print 33 1 #at -1 20))
				(mamaComingLater (Print 33 2 #at -1 20))
				(else  (Print 33 3 #at -1 20))
			)
		)
	)
)

(instance rm33Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0004) (curRoom newRoom: 32))
		(if
		(and (== state 5) (& (ego onControl: origin) $0020))
			(self changeState: 6)
		)
		(if (and (== state 7) (== (whipScript state?) 1))
			(self cue:)
		)
		(if
		(and mamaInRoom (== currentStatus egoNORMAL) (> (ego x?) 170))
			(= currentStatus egoSTOPPED)
			(HandsOff)
			(mama view: 308 posn: 173 104)
			(Print 33 4)
			(self changeState: 12)
			(whipScript changeState: 5)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(2
				(HandsOff)
				(= mamaInRoom 1)
				(= seconds 3)
			)
			(3
				(Print 33 30 #at -1 20)
				(Print 33 31)
				(Print 33 32 #at -1 20)
				(Print 33 33)
				(Print 33 34 #at -1 20)
				(Print 33 35)
				(Print 33 36 #at -1 20)
				(= seconds 3)
			)
			(4
				(Print 33 37 #at -1 20)
				(Print 33 38 #at -1 20)
				(Print 33 39)
				(Print 33 40 #at -1 20)
				(Print 33 41)
				(Print 33 42 #at -1 152)
				(HandsOn)
			)
			(6
				(= seconds 0)
				(= currentStatus egoSTOPPED)
				(= mamaInRoom 3)
				(mama loop: 3 show: setMotion: MoveTo 108 113 self)
				(ego setScript: whipScript)
				(Print 33 43 #at -1 15 #width 280 #draw)
				(Print 33 44)
				(Print 33 45)
			)
			(7
				(mama loop: 0)
				(Print 33 46 #at -1 15 #width 280 #draw)
			)
			(8
				(mama
					view: 307
					setLoop: 0
					posn: 107 112
					setPri: 12
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(9
				(mama
					setLoop: 1
					cel: 0
					posn: 105 112
					setPri: 7
					cycleSpeed: 1
					setCycle: CycleTo 6 1 self
				)
				(whipScript changeState: 2)
				(Print 33 47 #at -1 20)
			)
			(10
				(mama setLoop: 1 setCel: 255)
				(whipScript changeState: 3)
			)
			(11
				(aCloset setCel: 255)
				(mama
					view: 308
					setLoop: 0
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 167 105 self
				)
				(Print 33 48 #draw)
			)
			(12
				(mama setLoop: 1 setCycle: Forward cycleSpeed: 1)
				(= seconds 7)
			)
			(13
				(Print 33 49)
				(Print 33 50)
				(Print 33 51 #at -1 152)
				(= currentStatus egoDEAD)
			)
			(14 (= mamaInRoom 2) (= seconds 5))
			(15
				(Print 33 52)
				(Print 33 53)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look<below/bed') (Print 33 5))
		(if (Said 'look>')
			(if (Said '/(door<cabinet),cabinet')
				(cond 
					((== closetIsOpen FALSE) (Print 33 6))
					(mamaInRoom (Print 33 7))
					(else (Print 33 8))
				)
			)
			(if (Said '/door') (Print 33 9))
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: origin) $0020)) (NotClose))
					((== drawerIsOpen FALSE) (Print 33 6))
					(mamaInRoom (Print 33 7))
					(else
						(Ok)
						(if ((inventory at: iSewingKit) ownedBy: curRoomNum)
							(Print 33 10)
						else
							(Print 33 11)
						)
					)
				)
			)
			(if (Said '/bed')
				(Print 33 12)
				(if (> filthLevel 10) (Print 33 13 #at -1 152))
			)
			(if (Said '/bimbo')
				(cond 
					((== (User canControl:) FALSE) (Print 33 7))
					((not mamaInRoom) (Print 33 14))
					(else
						(Print 33 15)
						(aBigMama posn: 273 58)
						(Timer setReal: aBigMama 5)
						(HandsOff)
					)
				)
			)
			(if (Said '/art,brick') (Print 33 16))
			(if (Said '[/cabin,airport]')
				(Print 33 17)
				(if mamaInRoom (Print 33 18))
			)
		)
		(if (Said 'open>')
			(if (Said '/(door<cabinet),door,cabinet')
				(cond 
					((& (ego onControl:) $0002) (Print 33 19))
					((ego inRect: 127 117 333 222) (Print 33 20))
					((not (& (ego onControl: origin) $0010)) (NotClose))
					((== closetIsOpen TRUE) (ItIs))
					(mamaInRoom (Print 33 7))
					(else (Ok) (= closetIsOpen TRUE) (aCloset force: 1 open:))
				)
			)
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: 1) $0020)) (NotClose))
					((== drawerIsOpen TRUE) (ItIs))
					(mamaInRoom (Print 33 7))
					(else (Ok) (= drawerIsOpen TRUE) (aDrawer force: 1 open:))
				)
			)
		)
		(if (Said 'close>')
			(if (Said '/(door<cabinet),door,cabinet')
				(cond 
					((& (ego onControl:) $0002) (Print 33 21))
					((ego inRect: 127 117 333 222) (Print 33 22))
					((not (& (ego onControl: origin) $0010)) (NotClose))
					((== closetIsOpen FALSE) (ItIs))
					(mamaInRoom (Print 33 23))
					(else (Ok) (= closetIsOpen FALSE) (aCloset force: 1 close:))
				)
			)
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: origin) $0020)) (NotClose))
					((== drawerIsOpen FALSE) (ItIs))
					(mamaInRoom (Print 33 23))
					(else (Ok) (= drawerIsOpen FALSE) (aDrawer force: 1 close:))
				)
			)
		)
		(if (Said 'get/chain,dirt') (Print 33 24))
		(if (Said 'get/kit')
			(cond 
				((not (& (ego onControl: origin) $0020)) (NotClose))
				((not ((inventory at: iSewingKit) ownedBy: curRoomNum)) (AlreadyTook))
				((!= drawerIsOpen TRUE) (Print 33 14))
				((or mamaInRoom (!= currentStatus egoNORMAL)) (NotNow))
				(else (Ok) (ego get: 12) (theGame changeScore: 6))
			)
		)
		(if
			(or
				(Said '(get<on,in),lie,board/bed')
				(Said '(get<off),drain/bra')
				(Said 'ok,play,(get<naked)')
				(Said 'get/naked')
				(Said 'caress,(make<enjoy),embrace,embrace/bimbo')
				(Said 'bath,(bath<using),(nap<using),copulate/bed,bimbo')
			)
			(if (not mamaInRoom) (Print 33 25) else (Print 33 26))
		)
		(if (Said 'call/bimbo')
			(cond 
				((== (User canControl:) 0) (Print 33 7))
				((not mamaInRoom) (Print 33 14))
				(else
					(Print (Format @str 33 27 introductoryPhrase))
					(Print 33 28)
				)
			)
		)
		(if (Said 'get')
			(if mamaInRoom (Print 33 7) else (Print 33 29))
		)
	)
)

(instance whipScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					loop: 2
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 119 113 self
				)
			)
			(1 (ego setLoop: 1))
			(2 (ego hide:))
			(3
				(ego
					view: 307
					illegalBits: 0
					ignoreActors:
					loop: 2
					cel: 0
					posn: 117 85
					setPri: 9
					setStep: 5 5
					show:
					setMotion: JumpTo 194 94 self
				)
			)
			(4
				(rm33Script changeState: 11)
				(self changeState: 5)
			)
			(5
				(ego
					view: 307
					illegalBits: 0
					ignoreActors:
					setLoop: 3
					posn: 184 84
					setPri: 5
					setCycle: Forward
					setMotion: 0
				)
				(= cycles (Random 2 11))
			)
			(6
				(ego cycleSpeed: (Random 0 7))
				(= cycles (Random 6 22))
				(= state 5)
			)
		)
	)
)

(instance aBigMama of Prop
	(properties)
	
	(method (cue)
		(Print 33 54)
		(Print 33 55 #at 66 155 #width 190)
		(self posn: 273 1058)
		(HandsOn)
	)
)
