;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include sci.sh)
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

(local
	mamaInRoom
	mama
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

(instance rm33 of Rm
	(properties
		picture 33
	)
	
	(method (init)
		(Load rsVIEW 100)
		(Load rsVIEW 306)
		(Load rsVIEW 307)
		(Load rsVIEW 308)
		(Load rsVIEW 305)
		(Load rsVIEW 313)
		(Load rsSOUND 27)
		(Load rsSOUND 28)
		(Load rsSCRIPT 991)
		(super init:)
		(mOdrawer init:)
		(mCdrawer init:)
		(addToPics add: aView1 aView2 doit:)
		(aBigMama
			view: 313
			posn: 273 1058
			setPri: 15
			setCycle: Fwd
			init:
		)
		(aPorthole setCycle: Fwd cycleSpeed: 10 isExtra: 1 init:)
		(NormalEgo)
		(ego posn: 109 112 loop: 0 init:)
		(self setRegions: 300 setScript: rm33Script)
		(if (== currentEgoView 100)
			(cond 
				((== 1 (++ metMama))
					(= mamaInRoom 1)
					(= roomState 1)
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
				((> 3 (Random 1 5)) (= roomState 0))
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
					(= roomState 3)
					((= mama (Act new:))
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
		(aCloset doorCtrl: 16 doorBlock: 0 roomCtrl: 0 init:)
		(aDrawer doorCtrl: 32 doorBlock: 0 roomCtrl: 0 init:)
		(if debugging
			(RedrawCast)
			(switch roomState
				(1 (Print 33 0 #at -1 20))
				(2 (Print 33 1 #at -1 20))
				(3 (Print 33 2 #at -1 20))
				(else  (Print 33 3 #at -1 20))
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 991)
		(super dispose:)
	)
)

(instance rm33Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0004) (curRoom newRoom: 32))
		(if
		(and (== state 5) (& (ego onControl: 1) $0020))
			(self changeState: 6)
		)
		(if (and (== state 7) (== (whipScript state?) 1))
			(self cue:)
		)
		(if
		(and mamaInRoom (== currentStatus 0) (> (ego x?) 170))
			(= currentStatus 1000)
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
				(Print 33 42 #at -1 130)
				(HandsOn)
			)
			(6
				(= seconds 0)
				(= currentStatus 1000)
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
					setCycle: End self
				)
			)
			(9
				(mama
					setLoop: 1
					cel: 0
					posn: 105 112
					setPri: 7
					cycleSpeed: 1
					setCycle: CT 6 1 self
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
				(mama setLoop: 1 setCycle: Fwd cycleSpeed: 1)
				(= seconds 7)
			)
			(13
				(Print 33 49)
				(Print 33 50)
				(Print 33 51 #at -1 130)
				(= currentStatus 1001)
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
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look<below/bed') (Print 33 5))
		(if (Said 'look>')
			(if (Said '/(door<cabinet),cabinet')
				(cond 
					((== closetIsOpen 0) (Print 33 6))
					(mamaInRoom (Print 33 7))
					(else (Print 33 8))
				)
			)
			(if (Said '/door') (Print 33 9))
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: 1) $0020)) (NotClose))
					((== drawerIsOpen 0) (Print 33 6))
					(mamaInRoom (Print 33 7))
					(else
						(Ok)
						(if ((inventory at: 12) ownedBy: curRoomNum)
							(Print 33 10)
						else
							(Print 33 11)
						)
					)
				)
			)
			(if (Said '/bed')
				(Print 33 12)
				(if (> filthLevel 10) (Print 33 13 #at -1 130))
			)
			(if (Said '/bimbo')
				(cond 
					((== (User canControl:) 0) (Print 33 7))
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
					((not (& (ego onControl: 1) $0010)) (NotClose))
					((== closetIsOpen 1) (ItIs))
					(mamaInRoom (Print 33 7))
					(else (Ok) (= closetIsOpen 1) (aCloset force: 1 open:))
				)
			)
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: 1) $0020)) (NotClose))
					((== drawerIsOpen 1) (ItIs))
					(mamaInRoom (Print 33 7))
					(else
						(Ok)
						(mOdrawer play:)
						(= drawerIsOpen 1)
						(aDrawer force: 1 open:)
					)
				)
			)
		)
		(if (Said 'close>')
			(if (Said '/(door<cabinet),door,cabinet')
				(cond 
					((& (ego onControl:) $0002) (Print 33 21))
					((ego inRect: 127 117 333 222) (Print 33 22))
					((not (& (ego onControl: 1) $0010)) (NotClose))
					((== closetIsOpen 0) (ItIs))
					(mamaInRoom (Print 33 23))
					(else (Ok) (= closetIsOpen 0) (aCloset force: 1 close:))
				)
			)
			(if (Said '/bureau,new,buffet,bureau')
				(cond 
					((not (& (ego onControl: 1) $0020)) (NotClose))
					((== drawerIsOpen 0) (ItIs))
					(mamaInRoom (Print 33 23))
					(else
						(Ok)
						(mCdrawer play:)
						(= drawerIsOpen 0)
						(aDrawer force: 1 close:)
					)
				)
			)
		)
		(if (Said 'get/chain,dirt') (Print 33 24))
		(if (Said 'get/kit')
			(cond 
				((not (& (ego onControl: 1) $0020)) (NotClose))
				((not ((inventory at: 12) ownedBy: curRoomNum)) (AlreadyTook))
				((!= drawerIsOpen 1) (Print 33 14))
				((or mamaInRoom (!= currentStatus 0)) (NotNow))
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
					setCycle: Fwd
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

(instance aView1 of PV
	(properties
		y 77
		x 172
		view 305
		loop 5
		priority 3
		signal $4000
	)
)

(instance aView2 of PV
	(properties
		y 86
		x 222
		view 305
		priority 5
		signal $4000
	)
)

(instance aPorthole of Prop
	(properties
		y 74
		x 172
		view 305
		loop 1
		priority 4
		signal $4000
	)
)

(instance aCloset of Door
	(properties
		y 96
		x 137
		view 305
		loop 3
		priority 6
		msgLook {mOdrawer}
		msgLookLock {rm33}
		msgLocked 4256
		msgExcept 4277
		msgFunny 4300
		msgCloser 4320
	)
)

(instance aDrawer of Door
	(properties
		y 88
		x 161
		view 305
		loop 2
		priority 5
		msgLook {mOdrawer}
		msgLookLock {rm33}
		msgLocked 4256
		msgExcept 4277
		msgFunny 4300
		msgCloser 4320
	)
)
