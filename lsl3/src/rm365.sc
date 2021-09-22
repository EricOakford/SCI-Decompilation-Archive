;;; Sierra Script 1.0 - (do not remove this comment)
(script# 365)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm365 0
)

(procedure (BambiSays)
	(systemWindow color: vLRED back: vGREY)
	(Print &rest #at 195 20 #title {Bambi says} #width 115)
	(systemWindow color: vBLACK back: vGREY)
)

(procedure (YouSay)
	(systemWindow color: vLBLUE back: vGREY)
	(Print &rest #at 10 111 #title {You say} #width 150)
	(systemWindow color: vBLACK back: vGREY)
)

(instance rm365 of Room
	(properties
		picture 365
	)
	
	(method (init)
		(Load VIEW 366)
		(Load VIEW 725)
		(Load PICTURE 366)
		(Load PICTURE 99)
		(Load SOUND 11)
		(Load SOUND 365)
		(Load SOUND 8)
		(Load SOUND 9)
		(Load SOUND 261)
		(super init:)
		(Bset fScrewedBambi)
		(HandsOff)
		(StatusLine disable:)
		(TheMenuBar hide: state: FALSE)
		(aDoor init:)
		(aLid init:)
		(aBambi init:)
		(addToPics add: atpBed doit:)
		(self setScript: RoomScript)
		(ego
			view: 365
			loop: 3
			cel: 0
			setCycle: Walk
			illegalBits: 0
			posn: 115 112
			ignoreActors:
			init:
		)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(aDoor setCycle: EndLoop self)
			)
			(2
				(music stop:)
				(soundFX number: 11 loop: 1 play:)
				(aBambi cel: 1)
				(ego cel: 1)
				(aDoor stopUpd:)
				(= seconds 3)
			)
			(3
				(BambiSays 365 0)
				(= seconds 3)
			)
			(4
				(aBambi
					view: 393
					setLoop: 1
					setMotion: MoveTo 180 110 self
				)
			)
			(5
				(aBambi setLoop: 4 cel: 0 stopUpd:)
				(= seconds 3)
			)
			(6
				(music number: 8 loop: -1 play:)
				(BambiSays 365 1)
				(= seconds 3)
			)
			(7
				(Print 365 2 #at -1 144)
				(= seconds 3)
			)
			(8
				(ego view: 725 setLoop: 0 setMotion: MoveTo 166 110 self)
			)
			(9
				(YouSay 365 3)
				(= seconds 3)
			)
			(10
				(aBambi hide:)
				(ego
					view: 366
					setLoop: 0
					posn: (aBambi x?) (- (aBambi y?) 20)
					setCycle: EndLoop self
				)
			)
			(11
				(ego
					viewer: egoHumpCycler
					posn: 201 94
					setLoop: 1
					setCycle: Forward
				)
				(= seconds 7)
			)
			(12
				(BambiSays 365 4)
				(= seconds 5)
			)
			(13
				(Print 365 5)
				(= cycles 11)
			)
			(14
				(soundFX number: 365 loop: 1 play:)
				(aLid setCycle: EndLoop self)
			)
			(15
				(curRoom drawPic: 366 WIPELEFT)
				(aLid dispose:)
				(aDoor dispose:)
				(ego
					viewer: egoHumpCycler
					loop: 2
					setPri: 12
					cel: 0
					setCycle: Forward
				)
				(aBambi
					posn: 187 75
					view: 366
					loop: 3
					setPri: 4
					cel: 0
					setCycle: Forward
					show:
				)
				(music number: 261 loop: -1 play:)
				(= seconds 5)
			)
			(16
				(BambiSays 365 6)
				(= seconds 5)
			)
			(17
				(Print 365 7)
				(= seconds 3)
			)
			(18
				(YouSay 365 8)
				(= seconds 3)
			)
			(19
				(BambiSays 365 9)
				(ego viewer: 0 stopUpd:)
				(aBambi stopUpd:)
				(= seconds 3)
			)
			(20
				(BambiSays 365 10)
				(= seconds 3)
			)
			(21
				(YouSay 365 11)
				(= seconds 3)
			)
			(22
				(music fade:)
				(curRoom drawPic: 99 IRISIN)
				(cast eachElementDo: #hide)
				(= seconds 3)
			)
			(23
				(Print 365 12)
				(music number: 9 loop: 1 play:)
				(Print 365 13)
				(= seconds 3)
			)
			(24
				(systemWindow color: myTextColor back: myBackColor)
				(= currentStatus egoNORMAL)
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance aBambi of Actor
	(properties
		y 108
		x 124
		view 365
		loop 2
	)
	
	(method (init)
		(super init:)
		(self
			illegalBits: 0
			setPri: 7
			setCycle: Walk
			ignoreActors:
		)
	)
)

(instance aDoor of Prop
	(properties
		y 62
		x 118
		view 365
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 2 ignoreActors:)
	)
)

(instance aLid of Prop
	(properties
		y 83
		x 235
		view 365
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 12 ignoreActors:)
	)
)

(instance atpBed of PicView
	(properties
		y 118
		x 199
		view 365
		priority 4
		signal ignrAct
	)
)

(instance humpCycler of Code	;EO: This seesm to be an unused duplicate of egoHumpCycler
	(method (doit &tmp randVal)
		(cond 
			((<= filthLevel 2)
				(ego stopUpd:)
				(aBambi stopUpd:)
			)
			((not (Random 0 9))
				(= randVal (Random 0 5))
				(ego cycleSpeed: randVal)
				(aBambi cycleSpeed: randVal)
			)
		)
	)
)

(instance egoHumpCycler of Code
	(method (doit &tmp randVal)
		(cond 
			((<= filthLevel 2)
				(ego stopUpd:)
				(aBambi stopUpd:)
			)
			((not (Random 0 9))
				(= randVal (Random 0 5))
				(ego cycleSpeed: randVal)
				(aBambi cycleSpeed: randVal)
			)
		)
	)
)
