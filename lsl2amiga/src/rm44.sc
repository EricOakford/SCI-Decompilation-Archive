;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include sci.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm44 0
)

(local
	local0
	maidInRoom
	[local2 2]
)
(instance rm44 of Rm
	(properties
		picture 44
		horizon 1
	)
	
	(method (init)
		(Load rsVIEW 152)
		(Load rsVIEW 416)
		(if (and (ego has: 15) (ego has: 16))
			(Load rsVIEW 150)
			(Load rsVIEW 151)
		)
		(Load rsSOUND 12)
		(theSound init:)
		(super init:)
		(addToPics
			add: aTable aLamp aTable2 aBathroom aPainting
			doit:
		)
		(if ((inventory at: 18) ownedBy: curRoomNum)
			(aSoap stopUpd: init:)
		)
		(aBoat
			setCel: (Random 0 4)
			setPri: 0
			setStep: 1 1
			moveSpeed: 2
			illegalBits: 0
			init:
			setScript: boatScript
		)
		(NormalEgo)
		(ego posn: 159 162 init:)
		(if (== currentEgoView 100)
			(Load rsVIEW 417)
			(Load rsVIEW 418)
			(Load rsVIEW 419)
			(Load rsVIEW 436)
			(aMaid
				posn: 160 234
				illegalBits: 0
				setCycle: Walk
				stopUpd:
				init:
			)
			(aBigFace
				view: 436
				setLoop: 0
				posn: 149 1060
				setPri: 15
				init:
			)
			(aBrother
				illegalBits: 0
				posn: 160 234
				setCycle: Walk
				init:
			)
		)
		(self setRegions: 400 setScript: rm44Script)
	)
)

(instance rm44Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0004) (curRoom newRoom: 40))
		(if
		(and (== state 7) (ego inRect: 100 120 127 130))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (== currentStatus 0) (== currentEgoView 100))
					(= seconds 10)
				)
			)
			(1
				(if
				(and (== currentStatus 0) (== currentEgoView 100))
					(= maidInRoom 1)
					(aMaid setMotion: MoveTo 160 132 self)
				)
			)
			(2
				(Face aMaid ego 1)
				(= seconds 3)
			)
			(3
				(Print 44 58 #at -1 15 #width 280)
				(Print 44 59 #at -1 130)
				(= seconds 8)
			)
			(4
				(Print (Format @str 44 60 tritePhrase))
				(aMaid setMotion: MoveTo 160 234 self)
			)
			(5
				(= maidInRoom 0)
				(aMaid dispose:)
			)
			(6
				(= seconds (= cycles 0))
				(Print 44 61 #at -1 20)
				(aMaid setMotion: MoveTo 130 130 self)
			)
			(7
				(aMaid
					illegalBits: 0
					ignoreActors:
					view: 418
					loop: 0
					cel: 0
					setPri: 9
					posn: 127 110
				)
				(Print (Format @str 44 62 tritePhrase) #at -1 20 #draw)
			)
			(8
				(HandsOff)
				(= currentStatus 1000)
				(ego
					illegalBits: 0
					ignoreActors:
					view: 418
					loop: 1
					cel: 0
					posn: 111 119
					setPri: 8
				)
				(= seconds 3)
			)
			(9
				(Print 44 63 #at -1 20 #draw)
				(= seconds 3)
			)
			(10
				(ego cycleSpeed: 1 setCycle: End self)
				(aMaid cycleSpeed: 1 setCycle: End)
			)
			(11 (= seconds 4))
			(12
				(ego hide:)
				(aMaid
					setLoop: 2
					cel: 0
					setCycle: Fwd
					posn: 137 104
					setPri: 8
				)
				(= seconds 3)
			)
			(13
				(aMaid setLoop: 3)
				(= seconds 2)
			)
			(14
				(aMaid setLoop: 2)
				(= seconds 4)
			)
			(15
				(aMaid setLoop: 4)
				(= seconds 2)
			)
			(16
				(aMaid setLoop: 2)
				(= seconds 4)
			)
			(17
				(aMaid setLoop: 5)
				(= seconds 2)
			)
			(18
				(aMaid setLoop: 2)
				(aBrother setScript: brotherScript)
			)
			(19
				(aBrother setCycle: End self)
				(if (> filthLevel 10)
					(Print 44 64 #at -1 20 #draw)
				else
					(Print 44 65)
				)
				(= seconds 3)
			)
			(20
				(aMaid setLoop: 6)
				(= seconds 4)
			)
			(21
				(if (> filthLevel 10) (Print 44 66) else (Print 44 67))
				(= currentStatus 1001)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look<below/bed')
			(Print 44 0)
			(Print 44 1)
			(Print 44 2 #at -1 130)
		)
		(if (Said '/burn') (Print 44 3))
		(if (Said 'look>')
			(if (Said '/bimbo')
				(cond 
					((not maidInRoom) (Print 44 4))
					((!= currentStatus 0) (NotNow))
					(else
						(aBigFace posn: 149 59)
						(Timer setReal: aBigFace 5)
						(HandsOff)
					)
				)
			)
			(if (Said '/cabinet,bathtub') (Print 44 5))
			(if (Said '/mirror')
				(if (== currentEgoView 100)
					(Print 44 6)
				else
					(Print 44 7)
					(Print 44 8 #at -1 130)
				)
			)
			(if (Said '/barstool,bathroom') (Print 44 9))
			(if
				(Said
					'/(airport<bath),basin,cabinet,bowl,bathroom,(airport<bath)'
				)
				(if ((inventory at: 18) ownedBy: curRoomNum)
					(Print 44 10)
				else
					(Print 44 11)
				)
			)
			(if (Said '/bureau') (Print 44 12))
			(if (Said '/bed') (Print 44 13))
			(if (Said '/art') (Print 44 14))
			(if (Said '<back/art') (Print 44 15))
			(if
				(or
					(Said '/mirror')
					(and
						(or (== currentEgoView 150) (== currentEgoView 151))
						(Said '/bikini')
					)
				)
				(cond 
					((== currentEgoView 150) (Print 44 16))
					((== currentEgoView 151) (Print 44 17))
					((== currentEgoView 149) (Print 44 18))
					(else (Print 44 19))
				)
			)
			(if (Said '/new,buffet')
				(if ((inventory at: 19) ownedBy: curRoomNum)
					(Print 44 20)
				else
					(Print 44 21)
				)
			)
			(if (Said '/cup')
				(Print 44 22)
				(Print 44 23 #at -1 130)
			)
			(if (Said '/beach') (Print 44 24))
			(if (Said '[/airport]')
				(Print 44 25)
				(if (& (ego onControl:) $0002)
					(if ((inventory at: 18) ownedBy: curRoomNum)
						(Print 44 10)
					else
						(Print 44 11)
					)
				else
					(Print 44 26)
					(if ((inventory at: 19) ownedBy: curRoomNum)
						(Print 44 20)
					)
				)
			)
		)
		(if (Said 'get/bowl') (Print 44 27))
		(if
			(or
				(Said 'crap,leak')
				(Said '/crap,leak')
				(Said '//crap,leak')
				(Said '/bathroom')
			)
			(if (ego inRect: 227 142 259 149)
				(ego loop: 2 setMotion: 0)
				(Print 44 28 #draw)
			else
				(NotClose)
			)
		)
		(if
			(and
				(== currentEgoView 151)
				(Said 'drain/soap,buck/([<bikini]')
			)
			(Print 44 29)
		)
		(if (Said 'wear,apply/flower') (Print 44 30))
		(if (Said '(jerk<on),apply/fluid,basin') (Print 44 31))
		(if (Said 'close,jerk/cup,curtain') (Print 44 32))
		(if maidInRoom
			(if (Said 'n') (Print 44 33) (Print 44 34))
			(if (Said 'call/bimbo')
				(Print (Format @str 44 35 introductoryPhrase))
				(Print 44 36)
			)
			(if (Said '(enjoy<make),(clit<have),copulate/bimbo')
				(cond 
					((!= currentStatus 0) (NotNow))
					((> state 5) (Print 44 37))
					(else (Ok) (self changeState: 6))
				)
			)
		)
		(if
			(or
				(Said '(board<to),(get<in),board/bed,nap')
				(Said '(lie<down),lie,nap')
			)
			(cond 
				((== (ego view?) 152) (YouAre))
				((!= currentStatus 0) (NotNow))
				((!= currentEgoView 100) (Print 44 38))
				((< (ego y?) 121) (Print 44 39))
				((not (ego inRect: 104 127 122 131)) (NotClose))
				(else
					(= currentStatus 1005)
					(Ok)
					(User canControl: 0 canInput: 1)
					(= currentEgoView (ego view?))
					(ego
						view: 152
						setLoop: 0
						cel: 0
						setCycle: Fwd
						cycleSpeed: 5
						setPri: 8
						setMotion: 0
						illegalBits: 0
						posn: 111 110
					)
				)
			)
		)
		(if
			(or
				(Said 'disembark/bed')
				(Said 'new,(awaken,new,get<up)')
			)
			(cond 
				((!= (ego view?) 152) (YouAre))
				((!= currentStatus 1005) (NotNow))
				(else (Ok) (ego posn: 110 129) (NormalEgo 3))
			)
		)
		(if
			(or
				(Said
					'wear,(alter<in),(conceal<on)/job,(bra<bathing),(,bottom,bikini'
				)
				(Said 'alter,(get<off),drain/bra,bra')
			)
			(cond 
				((!= currentStatus 0) (NotNow))
				(maidInRoom (Print 44 40))
				(
				(or (== currentEgoView 150) (== currentEgoView 151)) (Print 44 41))
				((and (ego has: 15) (ego has: 16))
					(if (ego inRect: 176 100 199 115)
						(Ok)
						(= currentEgoView 150)
						(ego view: currentEgoView)
						(theGame changeScore: 5)
						(Print 44 42 #at -1 130)
					else
						(Print 44 43)
					)
				)
				((or (ego has: 15) (ego has: 16)) (Print 44 44))
				(else (DontHave))
			)
		)
		(if
			(or
				(Said 'alter,wear,(conceal<on)/bra,bra')
				(Said 'alter,drain,(get<off)/(bra<bathing),job')
			)
			(if
			(or (== currentEgoView 150) (== currentEgoView 151))
				(Print 44 45)
			else
				(Print 44 46)
			)
		)
		(if (Said 'get/bathtub,bath') (Print 44 47))
		(if (Said 'get/match')
			(cond 
				((not ((inventory at: 19) ownedBy: curRoomNum)) (AlreadyTook))
				((not (ego inRect: 70 127 103 139)) (NotClose))
				(else
					(Ok)
					(ego get: 19)
					(theGame changeScore: 2)
					(Print 44 48)
					(Print 44 49 #at -1 130)
					(theSound play:)
					(Print 44 50)
				)
			)
		)
		(if (Said 'get/soap')
			(cond 
				((not ((inventory at: 18) ownedBy: curRoomNum)) (AlreadyTook))
				((not (ego inRect: 222 131 243 145)) (NotClose))
				(else
					(Ok)
					(ego get: 18)
					(theGame changeScore: 2)
					(aSoap hide:)
					(Print 44 51 #at 15 -1 #width 280 #draw)
					(Print 44 52 #at -1 130)
				)
			)
		)
		(if
			(or
				(Said 'conceal,conceal/(,bikini/buck,bill')
				(Said 'conceal,conceal/buck,bill/(,bikini')
			)
			(cond 
				((!= currentStatus 0) (NotNow))
				(maidInRoom (Print 44 40))
				((== currentEgoView 151) (Print 44 53))
				((!= currentEgoView 150) (Print 44 54))
				((ego inRect: 176 100 199 115)
					(Ok)
					(= stuffedBra 6)
					(= currentEgoView 151)
					(NormalEgo)
					(theGame changeScore: 12)
					(Print 44 55 #at -1 130)
				)
				(else (Print 44 56))
			)
		)
		(if
			(or
				(Said 'conceal,conceal/(,bikini/soap')
				(Said 'conceal,conceal/soap/(,bikini')
			)
			(cond 
				((!= currentStatus 0) (NotNow))
				(maidInRoom (Print 44 40))
				((== currentEgoView 151) (Print 44 53))
				((!= currentEgoView 150) (Print 44 54))
				((not (ego has: 18)) (Print 44 57))
				((ego inRect: 176 100 199 115)
					(Ok)
					(= stuffedBra 18)
					(= currentEgoView 151)
					(NormalEgo)
					(theGame changeScore: 12)
					(Print 44 55 #at -1 130)
				)
				(else (Print 44 56))
			)
		)
	)
)

(instance brotherScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(aBrother setMotion: MoveTo 160 160 self)
			)
			(2
				(Print 44 68)
				(Print 44 69)
				(aBrother setMotion: MoveTo 150 122 self)
			)
			(3
				(aBrother setLoop: 1 cel: 0)
				(= seconds 3)
			)
			(4
				(rm44Script cue:)
				(self dispose:)
			)
		)
	)
)

(instance boatScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(aBoat setMotion: MoveTo 98 85 self)
			)
			(2
				(aBoat stopUpd:)
				(= seconds (Random 5 15))
			)
			(3
				(aBoat
					setCel: (Random 0 4)
					setMotion: MoveTo 179 85 self
				)
			)
			(4
				(aBoat setCel: (Random 0 4) stopUpd:)
				(self changeState: 0)
			)
		)
	)
)

(instance aBigFace of Prop
	(properties)
	
	(method (cue)
		(Print 44 70 #draw)
		(Print 44 71 #at 55 155 #width 210)
		(self posn: 149 1060)
		(HandsOn)
	)
)

(instance theSound of Sound
	(properties
		number 12
	)
)

(instance aTable of PV
	(properties
		y 132
		x 81
		view 416
		priority 9
		signal $4000
	)
)

(instance aLamp of PV
	(properties
		y 96
		x 110
		view 416
		cel 1
		priority 4
		signal $4000
	)
)

(instance aTable2 of PV
	(properties
		y 106
		x 108
		view 416
		cel 2
		priority 3
		signal $4000
	)
)

(instance aBathroom of PV
	(properties
		y 126
		x 241
		view 416
		cel 3
		priority 9
		signal $4000
	)
)

(instance aPainting of PV
	(properties
		y 97
		x 82
		view 416
		cel 5
		priority 4
		signal $4000
	)
)

(instance aSoap of View
	(properties
		y 122
		x 256
		view 416
		cel 4
		priority 10
		signal $4000
	)
)

(instance aBoat of Act
	(properties
		y 84
		x 179
		view 416
		loop 1
		signal $4000
	)
)

(instance aMaid of Act
	(properties
		view 417
		loop 3
	)
)

(instance aBrother of Act
	(properties
		view 419
		signal $4000
	)
)
