;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Timer)
(use Extra)
(use Sound)
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
	aMama
	drawerIsOpen
	closetIsOpen
	roomState
)

(instance mOdrawer of Sound
	(properties
		number 27
		priority 100
	)
)

(instance mCdrawer of Sound
	(properties
		number 28
		priority 100
	)
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
		(Load SOUND 27)
		(Load SOUND 28)
		(Load SCRIPT JUMP)
		(super init:)
		(mOdrawer init:)
		(mCdrawer init:)
		(addToPics add: aView1 aView2 doit:)
		(aBigMama
			view: 313
			posn: 273 1058
			setPri: 15
			setCycle: Forward
			init:
		)
		(aPorthole
			setCycle: Forward
			cycleSpeed: 10
			isExtra: TRUE
			init:
		)
		(NormalEgo)
		(ego posn: 109 112 loop: 0 init:)
		(self setRegions: SHIP setScript: rm33Script)
		(if (== currentEgoView 100)
			(cond 
				((== 1 (++ metMama))
					(= mamaInRoom TRUE)
					(= roomState firstMama)
					((= aMama (Extra new:))
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
				((> 3 (Random 1 5))
					(= roomState notThisTime)
				)
				((> 3 (Random 1 5))
					(= roomState heresMama)
					((= aMama (Extra new:))
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
					((= aMama (Actor new:))
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
		(aCloset
			doorCtrl: cRED
			doorBlock: FALSE
			roomCtrl: 0
			init:
		)
		(aDrawer
			doorCtrl: cMAGENTA
			doorBlock: FALSE
			roomCtrl: 0
			init:
		)
		(if debugging
			(RedrawCast)
			(switch roomState
				(firstMama
					(Print 33 0 #at -1 20)
				)
				(heresMama
					(Print 33 1 #at -1 20)
				)
				(mamaComingLater
					(Print 33 2 #at -1 20)
				)
				(else
					(Print 33 3 #at -1 20)
				)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
)

(instance rm33Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cGREEN)
			(curRoom newRoom: 32)
		)
		(if (and (== state 5) (& (ego onControl: origin) cMAGENTA))
			(self changeState: 6)
		)
		(if (and (== state 7) (== (whipScript state?) 1))
			(self cue:)
		)
		(if (and mamaInRoom (== currentStatus egoNORMAL) (> (ego x?) 170))
			(= currentStatus egoSTOPPED)
			(HandsOff)
			(aMama view: 308 posn: 173 104)
			(Print 33 4)
			(self changeState: 12)
			(whipScript changeState: 5)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(2
				(HandsOff)
				(= mamaInRoom TRUE)
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
				(Print 33 42 #at -1 130)
				(HandsOn)
			)
			(6
				(= seconds 0)
				(= currentStatus egoSTOPPED)
				(= mamaInRoom 3)
				(aMama loop: 3 show: setMotion: MoveTo 108 113 self)
				(ego setScript: whipScript)
				(Print 33 43 #at -1 15 #width 280 #draw)
				(Print 33 44)
				(Print 33 45)
			)
			(7
				(aMama loop: 0)
				(Print 33 46 #at -1 15 #width 280 #draw)
			)
			(8
				(aMama
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
				(aMama
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
				(aMama setLoop: 1 setCel: 255)
				(whipScript changeState: 3)
			)
			(11
				(aCloset setCel: 255)
				(aMama
					view: 308
					setLoop: 0
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 167 105 self
				)
				(Print 33 48 #draw)
			)
			(12
				(aMama setLoop: 1 setCycle: Forward cycleSpeed: 1)
				(= seconds 7)
			)
			(13
				(Print 33 49)
				(Print 33 50)
				(Print 33 51 #at -1 130)
				(= currentStatus egoDYING)
			)
			(14
				(= mamaInRoom 2)
				(= seconds 5)
			)
			(15
				(Print 33 52)
				(Print 33 53)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look<below/bed')
			(Print 33 5)
		)
		(if (Said 'look>')
			(if (Said '/(door<cabinet),cabinet')
				(cond 
					((== closetIsOpen FALSE)
						(Print 33 6)
					)
					(mamaInRoom
						(Print 33 7)
					)
					(else
						(Print 33 8)
					)
				)
			)
			(if (Said '/door')
				(Print 33 9)
			)
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: origin) cMAGENTA))
						(NotClose)
					)
					((== drawerIsOpen FALSE)
						(Print 33 6)
					)
					(mamaInRoom
						(Print 33 7)
					)
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
				(if (> filthLevel 10)
					(Print 33 13 #at -1 130)
				)
			)
			(if (Said '/bimbo')
				(cond 
					((== (User canControl:) FALSE)
						(Print 33 7)
					)
					((not mamaInRoom)
						(Print 33 14)
					)
					(else
						(Print 33 15)
						(aBigMama posn: 273 58)
						(Timer setReal: aBigMama 5)
						(HandsOff)
					)
				)
			)
			(if (Said '/art,brick')
				(Print 33 16)
			)
			(if (Said '[/cabin,airport]')
				(Print 33 17)
				(if mamaInRoom
					(Print 33 18)
				)
			)
		)
		(if (Said 'open>')
			(if (Said '/(door<cabinet),door,cabinet')
				(cond 
					((& (ego onControl:) cBLUE)
						(Print 33 19)
					)
					((ego inRect: 127 117 333 222)
						(Print 33 20)
					)
					((not (& (ego onControl: origin) cRED))
						(NotClose)
					)
					((== closetIsOpen TRUE)
						(ItIs)
					)
					(mamaInRoom
						(Print 33 7)
					)
					(else
						(Ok)
						(= closetIsOpen TRUE)
						(aCloset force: TRUE open:)
					)
				)
			)
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: origin) cMAGENTA))
						(NotClose)
					)
					((== drawerIsOpen TRUE)
						(ItIs)
					)
					(mamaInRoom
						(Print 33 7)
					)
					(else
						(Ok)
						(mOdrawer play:)
						(= drawerIsOpen TRUE)
						(aDrawer force: TRUE open:)
					)
				)
			)
		)
		(if (Said 'close>')
			(if (Said '/(door<cabinet),door,cabinet')
				(cond 
					((& (ego onControl:) cBLUE)
						(Print 33 21)
					)
					((ego inRect: 127 117 333 222)
						(Print 33 22)
					)
					((not (& (ego onControl: origin) cRED))
						(NotClose)
					)
					((== closetIsOpen FALSE)
						(ItIs)
					)
					(mamaInRoom
						(Print 33 23)
					)
					(else
						(Ok)
						(= closetIsOpen FALSE)
						(aCloset force: TRUE close:)
					)
				)
			)
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: origin) cMAGENTA))
						(NotClose)
					)
					((== drawerIsOpen FALSE)
						(ItIs)
					)
					(mamaInRoom
						(Print 33 23)
					)
					(else
						(Ok)
						(mCdrawer play:)
						(= drawerIsOpen FALSE)
						(aDrawer force: TRUE close:)
					)
				)
			)
		)
		(if (Said 'get/chain,dirt')
			(Print 33 24)
		)
		(if (Said 'get/kit')
			(cond 
				((not (& (ego onControl: origin) cMAGENTA))
					(NotClose)
				)
				((not ((inventory at: iSewingKit) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((!= drawerIsOpen TRUE)
					(Print 33 14)
				)
				((or mamaInRoom (!= currentStatus egoNORMAL))
					(NotNow)
				)
				(else
					(Ok)
					(ego get: iSewingKit)
					(theGame changeScore: 6)
				)
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
			(if (not mamaInRoom)
				(Print 33 25)
			else
				(Print 33 26)
			)
		)
		(if (Said 'call/bimbo')
			(cond 
				((== (User canControl:) FALSE)
					(Print 33 7)
				)
				((not mamaInRoom)
					(Print 33 14)
				)
				(else
					(Print
						(Format @str 33 27 introductoryPhrase)
					)
					(Print 33 28)
				)
			)
		)
		(if (Said 'get')
			(if mamaInRoom
				(Print 33 7)
			else
				(Print 33 29)
			)
		)
	)
)

(instance whipScript of Script
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
			(1
				(ego setLoop: 1)
			)
			(2
				(ego hide:)
			)
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
	(method (cue)
		(Print 33 54)
		(Print 33 55 #at 66 155 #width 190)
		(self posn: 273 1058)
		(HandsOn)
	)
)

(instance aView1 of PicView
	(properties
		y 77
		x 172
		view 305
		loop 5
		priority 3
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 86
		x 222
		view 305
		priority 5
		signal ignrAct
	)
)

(instance aPorthole of Prop
	(properties
		y 74
		x 172
		view 305
		loop 1
		priority 4
		signal ignrAct
	)
)

(instance aCloset of Door
	(properties
		y 96
		x 137
		view 305
		loop 3
		priority 6
	)
)

(instance aDrawer of Door
	(properties
		y 88
		x 161
		view 305
		loop 2
		priority 5
	)
)
