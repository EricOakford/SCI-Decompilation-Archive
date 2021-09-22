;;; Sierra Script 1.0 - (do not remove this comment)
(script# 323)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm323 0
)
(synonyms
	(babe attorney)
)

(local
	[str 300]
	local300
	local301
)
(instance rm323 of Room
	(properties
		picture 323
		west 320
	)
	
	(method (init)
		(Load VIEW 326)
		(Load VIEW 327)
		(Load SOUND 20)
		(super init:)
		(addToPics
			add: atpProps
			add: atpDoorNorth
			add: atpDoorSouth
			doit:
		)
		(aSuzi init:)
		(aChair init:)
		(self setScript: RoomScript)
		(= str 0)
		(if (!= prevRoomNum 325)
			(= saveEgoX 41)
			(= saveEgoY 156)
			(= saveEgoLoop 0)
		)
		(if (== currentStatus 323)
			(HandsOff)
			(ego view: 326 setCel: 255)
			(= saveEgoLoop 0)
			(RoomScript changeState: 4)
		else
			(NormalEgo)
		)
		(ego
			posn: saveEgoX saveEgoY
			loop: saveEgoLoop
			observeBlocks: blockOne blockTwo wallBlockLeft wallBlockRight
			init:
		)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(cond 
				((== lawyerState LSfree)
					(++ lawyerState)
					(Print 323 0 #icon 324 5 0)
					(Print 323 1 #icon 324 5 0)
					(Print 323 2 #icon 324 5 0)
				)
				((== lawyerState LSdivorce)
					(++ lawyerState)
					(Print 323 3 #icon 324 5 0)
					(Print 323 4 #icon 324 5 0)
				)
			)
			(curRoom newRoom: 320)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(if (not (Btst fMetSuzi))
					(= seconds 3)
				)
			)
			(1
				(Bset fMetSuzi)
				(Print 323 41)
				(Print 323 42)
			)
			(2
				(HandsOff)
				(Ok)
				(ego illegalBits: 0)
				(cond 
					((> (ego x?) 119)
						(ego setMotion: MoveTo 119 132 self)
					)
					((< (ego x?) 90)
						(ego setMotion: MoveTo 90 132 self)
					)
					(else
						(ego setMotion: MoveTo (ego x?) 132 self)
					)
				)
			)
			(3
				(ego view: 326 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(= currentStatus egoSITTING)
				(User canInput: TRUE)
				(= cycles (Random 33 66))
			)
			(5
				(ego setLoop: (Random 1 4) cel: 0 setCycle: EndLoop self)
			)
			(6
				(= cycles (Random 11 33))
			)
			(7
				(ego setCycle: BegLoop self)
				(= state 3)
			)
			(8
				(HandsOff)
				(Ok)
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(9
				(NormalEgo loopS)
				(= currentStatus egoNORMAL)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'lie')
				(cond 
					((not (& (ego onControl:) cGREEN))
						(Print 323 5)
					)
					((== currentStatus egoSITTING)
						(YouAre)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(self changeState: 2)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool,barstool')
				)
				(cond 
					((== currentStatus egoNORMAL)
						(YouAre)
					)
					((!= currentStatus egoSITTING)
						(Print 323 6)
					)
					(else
						(self changeState: 8)
					)
				)
			)
			((Said 'address/babe')
				(cond 
					((!= currentStatus egoSITTING)
						(Print 323 7)
					)
					((!= local301 0)
						(Print 323 8)
					)
					((== lawyerState LSfree)
						(Printf 323 9 introductoryPhrase)
						(Format @str 323 10)
						(SuziScript changeState: 2)
					)
					(else
						(Print 323 11)
						(Format @str 323 12)
						(SuziScript changeState: 2)
					)
				)
			)
			((or (Said '/deed,land') (Said '//deed,land'))
				(cond 
					((!= currentStatus egoSITTING)
						(Print 323 13)
					)
					((!= local301 0)
						(Print 323 14)
					)
					((== lawyerState LSfree)
						(++ lawyerState)
						(theGame changeScore: 30)
						(Print 323 15)
						(Print 323 16)
						(Format @str 323 17)
						(SuziScript changeState: 2)
					)
					((== lawyerState LSwaiting4Deed)
						(Format @str 323 18)
						(SuziScript changeState: 2)
					)
					(else
						(Print 323 11)
						(Format @str 323 12)
						(SuziScript changeState: 2)
					)
				)
			)
			((or (Said '/decree') (Said '//decree'))
				(cond 
					((!= currentStatus egoSITTING)
						(Print 323 19)
					)
					((!= local301 0)
						(Print 323 14)
					)
					((or (== lawyerState LSfree) (== lawyerState LSwaiting4Deed))
						(Print 323 20)
						(Format @str 323 21)
						(SuziScript changeState: 2)
					)
					((== lawyerState LSdivorce)
						(++ lawyerState)
						(theGame changeScore: 40)
						(Print 323 22)
						(Format @str 323 23)
						(SuziScript changeState: 2)
					)
					((== lawyerState LSWaiting4Divorce)
						(Print 323 24)
						(Format @str 323 25)
						(SuziScript changeState: 2)
					)
					(else
						(Format @str 323 26)
						(SuziScript changeState: 2)
					)
				)
			)
			((Said '/equipment')
				(Print 323 27)
				(Print 323 28 #at -1 144)
			)
			((Said '/call')
				(Print 323 29)
			)
			((Said '/buffet,calf')
				(Print 323 30)
			)
			((Said '/barstool')
				(Print 323 31)
			)
			((Said '/art')
				(Print 323 32)
			)
			((Said '/door')
				(Print 323 33)
			)
			((Said '/cup')
				(Print 323 34)
			)
			((Said '/bookcase,bookcase,bookcase,cabinet,book')
				(Print 323 35)
			)
			((Said 'look>')
				(cond 
					((Said '/babe,eye')
						(switch local301
							(1 (Print 323 36))
							(3 (Print 323 37))
							(5 (Print 323 38))
							(6 (Print 323 39))
							(else 
								(Ok)
								(= saveEgoX (ego x?))
								(= saveEgoY (ego y?))
								(= saveEgoLoop (ego loop?))
								(if (== currentStatus 1004)
									(= currentStatus 323)
								)
								(curRoom newRoom: 325)
							)
						)
					)
					((Said '[/office,area]')
						(Print 323 40)
					)
				)
			)
		)
	)
)

(instance SuziScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 2 1)
		(switch (= state newState)
			(0
				(aSuzi loop: 3 setCel: 0 setCycle: 0)
				(= local301 0)
				(ChairScript changeState: 2)
				(= cycles 0)
				(= seconds (Random 5 10))
			)
			(1
				(cond 
					((== (= local301 (Random 0 7)) 1)
						(self changeState: 4)
					)
					((== local301 3)
						(self changeState: 18)
					)
					((== local301 5)
						(self changeState: 6)
					)
					((== local301 7)
						(self changeState: 16)
					)
					(else
						(self changeState: 0)
					)
				)
			)
			(2
				(= local301 2)
				(aSuzi view: 324 loop: 3 setCycle: Forward)
				(= cycles (Random 11 44))
			)
			(3
				(if (== str 0)
					(if (== lawyerState LSfree)
						(Format @str 323 43)
					else
						(Format @str 323 44)
					)
				)
				(Print @str
					#at -1 10
					#title {Suzi says}
					#mode teJustCenter
					#icon 324 5 0
				)
				(if (and (== lawyerState LSfree) (not local300))
					(= local300 1)
					(Print 323 45
						#at -1 10
						#title {Suzi says}
						#mode teJustCenter
						#icon 324 5 0
					)
				)
				(= str 0)
				(= cycles 22)
				(= state -1)
			)
			(4
				(aSuzi view: 324 loop: 4 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(self changeState: 0)
				(ChairScript cue:)
			)
			(6
				(= local301 0)
				(= seconds 2)
			)
			(8
				(= local301 5)
				(aSuzi
					view: 327
					loop: 3
					illegalBits: 0
					ignoreActors: 0
					setPri: -1
					loop: -1
					setCycle: Walk
					setMotion: MoveTo 232 125 self
				)
				(ChairScript changeState: 4)
			)
			(9
				(aSuzi setMotion: MoveTo 190 125 self)
			)
			(10
				(aSuzi setMotion: MoveTo 204 204 self)
			)
			(11
				(= cycles 15)
			)
			(12
				(= local301 6)
				(aSuzi
					loop: -1
					setCycle: Walk
					setMotion: MoveTo 199 169 self
				)
			)
			(13
				(aSuzi setMotion: MoveTo 190 125 self)
			)
			(14
				(aSuzi ignoreActors: 1 setMotion: MoveTo 232 125 self)
			)
			(15
				(aSuzi setMotion: MoveTo 238 130 self)
			)
			(16
				(aSuzi
					view: 324
					posn: 242 120
					loop: 0
					cel: 0
					setPri: 13
					setCycle: EndLoop self
				)
				(ChairScript changeState: 5)
			)
			(17
				(ChairScript changeState: 0)
				(self changeState: 0)
			)
			(18
				(aSuzi view: 324 loop: 1 cel: 0 setCycle: EndLoop self)
				(ChairScript changeState: 2)
			)
			(19
				(aSuzi loop: 2 setCycle: Forward)
				(= cycles (Random 11 33))
			)
			(20
				(aSuzi loop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(21
				(ChairScript changeState: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance ChairScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 3 4)
		(switch (= state newState)
			(0
				(= seconds (Random 5 10))
			)
			(1
				(if (< local301 4)
					(self cue:)
				else
					(self changeState: 0)
				)
			)
			(2
				(aChair loop: 2 cycleSpeed: 2 setCycle: Forward)
				(= seconds (Random 2 5))
			)
			(3
				(aChair setCel: 0 setCycle: 0)
				(self changeState: 0)
			)
			(4
				(aChair view: 329 posn: 247 133 loop: 3 stopUpd:)
				(= cycles (= seconds 0))
			)
			(5
				(aChair
					view: 329
					loop: 0
					posn: 247 133
					cel: 0
					setCycle: EndLoop
				)
			)
		)
	)
)

(instance atpProps of PicView
	(properties
		y 132
		x 240
		view 329
		loop 4
		priority 10
		signal ignrAct
	)
)

(instance aChair of Prop
	(properties
		y 133
		x 247
		view 329
		loop 2
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setPri: 9)
	)
)

(instance aSuzi of Actor
	(properties
		y 120
		x 242
		view 324
		loop 1
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			illegalBits: 0
			setScript: SuziScript
			setPri: 11
			stopUpd:
		)
	)
)

(instance blockOne of Block
	(properties
		top 199
		left 74
		bottom 333
		right 231
	)
)

(instance blockTwo of Block
	(properties
		top 217
		left -20
		bottom 333
		right 333
	)
)

(instance wallBlockLeft of Block
	(properties
		top 175
		left -20
		bottom 333
		right -3
	)
)

(instance wallBlockRight of Block
	(properties
		left 325
		bottom 333
		right 340
	)
)

(instance aPhone of Actor
	(properties
		y -4
		x 152
		view 323
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			loop: 4
			setPri: 5
			setStep: 1 1
		)
	)
)

(instance atpDoorSouth of PicView
	(properties
		y 103
		x 20
		view 323
		cel 3
		priority 13
		signal ignrAct
	)
)

(instance atpDoorNorth of PicView
	(properties
		y 95
		x 41
		view 323
		loop 1
		cel 3
		priority 11
		signal ignrAct
	)
)
