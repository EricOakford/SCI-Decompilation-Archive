;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm31 0
	wizard 1
	egoChair 2
	poof 3
	fenrus 4
	ErasmusPrint 5
	FenrusPrint 6
	HeroPrint 7
	ErasmusCastTeleport 8
	door1 9
	door2 10
)
(enum
	beMoreInteresting
	knowAllPunchlines
	attackedWizard
	castMagic
	reallyMustGo
	noSleeping
)

(local
	local0
	reasonForLeaving
	theSeconds
	[str 251]
	local254
	local255
	erasmusDrinking
	local257
	local258
	local259
	local260
	local261
	local262
	local263
)
(procedure (ErasmusPrint param1)
	(= theSeconds (+ param1 5))
	(Format @str 31 0 &rest)
	(wizard setScript: 0)
	(cls)
	(wizard setScript: wizPrint)
)

(procedure (FenrusPrint param1)
	(= theSeconds (+ param1 5))
	(Format @str 31 0 &rest)
	(fenrus setScript: 0)
	(cls)
	(fenrus setScript: ratPrint)
)

(procedure (HeroPrint)
	(Format @str 31 0 &rest)
	(ego setScript: 0)
	(cls)
	(ego setScript: egoPrint)
)

(procedure (ErasmusCastTeleport)
	(= enableErasmusTeaCountdown 1)
	(ego setCel: 2)
	(rm31 setScript: 0)
)

(procedure (LookErasmus)
	(= local254 1)
	(HeroPrint 31 1)
	;Behind the wizard's stern and forbidding appearance you see a twinkle in his eye
	; You suspect that he is not always as serious as he appears.
)

(procedure (LookFenrus)
	(= local254 4)
	(fenrus setLoop: 6 setCel: 0)
	(HeroPrint 31 2)
	;Fenrus looks back at you with a grin.
)

(procedure (LookWest)
	(= local254 0)
	(HeroPrint 31 3)
	;To the west, the Baron's castle can be seen, surrounded by forest.
)

(procedure (LookEast)
	(= local254 0)
	(HeroPrint 31 4)
	;To the east, the peaks of the Wolf's Bane Mountains are clearly visible.
)

(procedure (LookTable)
	(= local254 2)
	(HeroPrint 31 5)
	;The table is elaborately carved on the sides. Erasmus has a teacup on the table in front of him.
)

(procedure (LookCup)
	(= local254 1)
	(if local255
		(HeroPrint 31 6)
		;The tea in the cup sitting on the table seems to refill itself as the wizard drinks.
		else
		(HeroPrint 31 7)
		;The tea in the cup has a strange aroma.
		)
)

(procedure (LookCheese)
	(= local254 4)
	(HeroPrint 31 8)
	;The cheese looks awful, but Fenrus seems to enjoy it.
)

(procedure (LookHero)
	(= local254 3)
	(HeroPrint 31 9)
	;WOW!  A Real Hero!
)

(procedure (LookUp)
	(= local254 2)
	(HeroPrint 31 10)
	;The ceiling is domed and ornately decorated.
)

(procedure (LookDown)
	(= local254 2)
	(HeroPrint 31 11)
	;As you look down, you realize the steepness of the staircase you ascended to get here.
	;Either the wizard is a fitness fanatic, or he uses a lot of "teleport" spells.
)

(instance rm31 of Room
	(properties
		picture 31
		style $0004
	)
	
	(method (init)
		(HandsOff)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(LoadMany SCRIPT 131 132 133 134 135)
		(LoadMany TEXT 131 132 133 134 135)
		(LoadMany VIEW vWizardTable vFenrus vTeleportPink)
		(StatusLine enable:)
		(super init:)
		(NormalEgo)
		(ego init:)
		(= local258 (Random 50 100))
		(= local259 100)
		(= local260 (Random 1 7))
		(addToPics add: table shelf eachElementDo: #init doit:)
		(if (== prevRoomNum 32)
			(door1 posn: 144 119)
			(door2 posn: 177 119)
		)
		(door1 ignoreActors: init: setPri: 1 stopUpd:)
		(door2 ignoreActors: init: setPri: 1 stopUpd:)
		(wizBody init: setPri: 5 stopUpd:)
		(teacup init: setPri: 7 stopUpd:)
		(egoChair init: setPri: 5 ignoreActors: stopUpd:)
		(fenrus init: setPri: 9 ignoreActors:)
		(poof init: setPri: 14 ignoreActors:)
		(wizard
			init:
			setPri: 15
			ignoreActors:
			illegalBits: 0
			stopUpd:
		)
		(wizDrinking
			init:
			setPri: 14
			ignoreActors:
			illegalBits: 0
			stopUpd:
		)
		(if (== prevRoomNum 32)
			(ego
				view: vWizardTable
				setLoop: 4
				setCel: 0
				illegalBits: 0
				posn: 186 121
				stopUpd:
			)
			(self setScript: entryDelay 0 (ScriptID 131 1))
		else
			(ego illegalBits: 0 setPri: 8 posn: 200 208)
			(self setScript: entryDelay 0 (ScriptID 131 0))
		)
	)
	
	(method (doit)
		(cond 
			((and enableErasmusTeaCountdown (> local258 1)) (-- local258))
			((== local258 1)
				(= local258 (Random 150 250))
				(wizDrinking setScript: drinkTea)
			)
		)
		(cond 
			((> local259 1) (-- local259))
			((== local259 1)
				(= local259 (Random 200 300))
				(if
					(and
						(== (rm31 script?) (wizDrinking script?))
						(== (wizDrinking script?) (fenrus script?))
						(== (fenrus script?) (wizard script?))
						(== (wizard script?) (ego script?))
						(== (ego script?) 0)
					)
					(if (== local260 7) (= local260 0) else (++ local260))
					(self setScript: (ScriptID 135 local260))
				)
			)
		)
		(if local0 (= local0 0) (self setScript: teleportOut))
		(super doit:)
	)
	
	(method (dispose)
		(Bset VISITED_ERASMUS_TOWER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(cond 
					((or erasmusTalking fenrusTalking) (event claimed: TRUE))
					((MouseClaimed wizBody event shiftDown) (LookErasmus))
					((MouseClaimed onRat event shiftDown) (LookFenrus))
					((MouseClaimed onLWindow event shiftDown) (LookWest))
					(
						(and
							(MouseClaimed onRWindow event shiftDown)
							(not (MouseClaimed onCheese event shiftDown))
						)
						(LookEast)
					)
					(
						(and
							(MouseClaimed onTable event shiftDown)
							(not (MouseClaimed teacup event shiftDown))
						)
						(LookTable)
					)
					(
					(and (MouseClaimed teacup event shiftDown) (not erasmusDrinking)) (LookCup))
					((MouseClaimed onCheese event shiftDown) (LookCheese))
					((MouseClaimed ego event shiftDown) (LookHero))
					((MouseClaimed onCeiling event shiftDown) (LookUp))
					((MouseClaimed onStairs event shiftDown) (LookDown))
				)
			)
			(keyDown
				(cond 
					((== ENTER (event message?))
						(cls)
						(if erasmusTalking (wizPrint changeState: 2))
						(if fenrusTalking (ratPrint changeState: 2))
					)
					((or erasmusTalking fenrusTalking) (event claimed: TRUE))
				)
			)
			(saidEvent
				(cond 
					(erasmusTellingJoke
						(cond 
							((Said 'affirmative,please')
								(if (== (++ local262) 4)
									(ErasmusCastTeleport)
									(= reasonForLeaving knowAllPunchlines)
									(= local0 1)
								else
									(= saidYesToErasmus TRUE)
									((rm31 script?) cue:)
								)
							)
							((Said 'n') ((rm31 script?) cue:))
							((Said 'chuckle')
								(HighPrint 31 12)
								;You're strange!  Erasmus hasn't told the punchline yet.
								)
							(else (event claimed: TRUE)
								(HighPrint 31 13)
								;Just answer the question: Yes or No?
								)
						)
					)
					((Said 'chuckle')
						(switch (Random 0 5)
							(0
								(HighPrint 31 14)
								;YUK-YUK-YUK-GUFFAW!
								)
							(1
								(HighPrint 31 15)
								;A-HYUK, A-HYUK, A-HYUK.  GAWRSH!
								)
							(2
								(HighPrint 31 16)
								;Tee-Hee-Hee!
								)
							(3
								(HighPrint 31 17)
								;HO-HO-HO-HEE-HEE-HEE HA-HA-HA!
								)
							(4
								(HighPrint 31 18)
								;(Snicker!)  (Chuckle!)  (Titter!)
								)
							(5
								(HighPrint 31 19)
								;This is a serious game, Rosella!
								)
						)
					)
					((or (Said 'nap[/!*]') (Said 'go[<to]/nap')) (= reasonForLeaving noSleeping) (= local0 1))
					((Said 'fight,beat,chop,kill,throw') (= reasonForLeaving attackedWizard) (Bset ERASMUS_ATTACKED) (= local0 1))
					((Said 'cast') (= reasonForLeaving castMagic) (Bset ERASMUS_ATTACKED) (= local0 1))
					(
					(or (Said 'stand,done,go,done') (Said 'get<up,out')) (= reasonForLeaving reallyMustGo) (= local0 1))
					((Said 'look>')
						(cond 
							((Said '/wizard,erasmus') (LookErasmus))
							((Said '/fenrus,familiar') (LookFenrus))
							((Said '/window') (LookWest) (LookEast))
							((Said '/table,desk') (LookTable))
							((Said '/tea,cup,teacup') (LookCup))
							((Said '/cheese') (LookCheese))
							((Said '/chair') (= local254 2)
								(HeroPrint 31 20)
								;The chairs are rather large.  But then again, so is Erasmus.
								)
							((or (Said '<up') (Said '/ceiling')) (LookUp))
							(
							(or (Said '<down') (Said '/floor,ladder,staircase')) (LookDown))
						)
					)
					((Said 'play/game,maze')
						(if (>= [egoStats MAGIC] 20)
							(self setScript: (ScriptID 132 5))
						else
							(ErasmusPrint 4 31 21)
							;You must first develop more skill in the magical arts.
						)
					)
					((Said 'ask>')
						(if erasmusDrinking
							(HeroPrint 31 22)
							;It would be best to wait until Erasmus puts down his cup of tea before asking any more questions.
							(event claimed: 1)
						else
							(= local263 1)
							(cond 
								((or (Said '//open') (Said '//spell<open')) (self setScript: (ScriptID 132 0)))
								((or (Said '//fetch') (Said '//spell<fetch')) (self setScript: (ScriptID 132 1)))
								(
								(or (Said '//trigger') (Said '//spell<trigger')) (self setScript: (ScriptID 132 2)))
								((or (Said '//dazzle') (Said '//spell<dazzle')) (self setScript: (ScriptID 132 3)))
								((Said '//trap') (self setScript: (ScriptID 132 4)))
								((Said '//erana') (self setScript: (ScriptID 133 0)))
								(
									(or
										(Said '//protection,peace,hamlet')
										(Said '//spell<protection,peace,erana')
									)
									(self setScript: (ScriptID 133 1))
								)
								((Said '//zara') (self setScript: (ScriptID 133 2)))
								((Said '//baba') (self setScript: (ScriptID 133 3)))
								((Said '//curse') (self setScript: (ScriptID 133 4)))
								((Said '//countercurse') (self setScript: (ScriptID 133 5)))
								((Said '//fenrus,familiar,fenrus') (self setScript: (ScriptID 133 6)))
								((Said '//enry,enry,ermit,ermit') (self setScript: (ScriptID 133 7)))
								((Said '//initiate,institute') (self setScript: (ScriptID 133 8)))
								((Said '//mirror') (self setScript: (ScriptID 134 0)))
								((Said '//wizard,user,caster,mage,mage') (self setScript: (ScriptID 134 1)))
								((Said '//nincompoop') (self setScript: (ScriptID 134 2)))
								((Said '//necromancer') (self setScript: (ScriptID 134 3)))
								((Said '//magic,technocery') (self setScript: (ScriptID 134 4)))
								((Said '//game,maze') (self setScript: (ScriptID 134 5)))
								((Said '//warlock') (ErasmusPrint 5 31 23))
								((Said '//spell,scroll') (ErasmusPrint 7 31 24))
								((Said '//bandit') (ErasmusPrint 9 31 25))
								((Said '//erasmus') (ErasmusPrint 4 31 26))
								(else
									(event claimed: TRUE)
									(= local263 0)
									(if (== (++ local261) 6)
										(= reasonForLeaving beMoreInteresting)
										(= local0 1)
									else
										(ErasmusPrint 4 31 27)
										;Please!  The least you could do is ask me about something more interesting.
									)
								)
							)
							(if (and local263 (event claimed?))
								(SolvePuzzle POINTS_TALKTOERASMUS 1)
							)
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance wizPrint of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusTalking TRUE)
				(if (< (wizDrinking cel?) 4) (wizard setCycle: Forward))
				(cond 
					((> (ego cel?) 1) (ego setCycle: CycleTo 1 -1 self))
					((< (ego cel?) 1) (ego setCycle: CycleTo 1 1 self))
					(else (self cue:))
				)
			)
			(1
				(if (!= theSeconds 0)
					(Print
						@str
						#at 3 25
						#width 82
						#mode teJustCenter
						#title { Erasmus_}
						#dispose
						#time theSeconds
					)
					(= seconds theSeconds)
				else
					(Print @str #at 3 25 #width 82 #mode teJustCenter)
					(self cue:)
				)
			)
			(2
				(wizard setCycle: 0 setCel: 0 setScript: 0)
				(= erasmusTalking FALSE)
			)
		)
	)
)

(instance ratPrint of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= fenrusTalking TRUE)
				(fenrus setLoop: 5 setCycle: Forward)
				(if (< (ego cel?) 4)
					(ego setCycle: CycleTo 4 1 self)
				else
					(self cue:)
				)
			)
			(1
				(if (!= theSeconds 0)
					(Print
						@str
						#at 225 85
						#width 90
						#mode teJustCenter
						#title { Fenrus_}
						#dispose
						#time theSeconds
					)
					(= seconds theSeconds)
				else
					(Print @str #at 225 85 #width 90 #mode teJustCenter)
					(self cue:)
				)
			)
			(2
				(fenrus setCycle: 0 setLoop: 5 setCel: 0 setScript: 0)
				(= fenrusTalking FALSE)
			)
		)
	)
)

(instance egoPrint of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch local254
					(0
						(if (> (ego cel?) 0)
							(ego setCycle: CycleTo 0 -1 self)
						else
							(self cue:)
						)
					)
					(1
						(if (> (ego cel?) 1)
							(ego setCycle: CycleTo 1 -1 self)
						else
							(ego setCel: 1)
							(= cycles 1)
						)
					)
					(2
						(cond 
							((< (ego cel?) 2) (ego setCycle: CycleTo 2 1 self))
							((> (ego cel?) 2) (ego setCycle: CycleTo 2 -1 self))
							(else (self cue:))
						)
					)
					(3
						(if (< (ego cel?) 3)
							(ego setCycle: CycleTo 3 1 self)
						else
							(ego setCel: 3)
							(= cycles 1)
						)
					)
					(4
						(if (< (ego cel?) 4)
							(ego setCycle: CycleTo 4 1 self)
						else
							(fenrus setLoop: 6)
							(= cycles 2)
						)
					)
				)
			)
			(1
				(Print @str #at -1 15)
				(if (== (fenrus loop?) 6) (fenrus setLoop: 5))
				(ego setScript: 0)
			)
		)
	)
)

(instance drinkTea of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusDrinking TRUE)
				(wizard hide:)
				(teacup hide:)
				(self cue:)
			)
			(1
				(wizDrinking setCycle: EndLoop self)
			)
			(2 (= cycles 8))
			(3
				(wizDrinking setCycle: BegLoop self)
			)
			(4
				(= erasmusDrinking FALSE)
				(wizard show:)
				(teacup show:)
				(wizDrinking setCel: 0 setScript: 0)
			)
		)
	)
)

(instance teleportOut of Script
	(properties)
	
	(method (doit)
		(if (and (not erasmusTalking) (not fenrusTalking)) (self cue:))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if erasmusTellingJoke (= erasmusTellingJoke 0))
				(= local258 0)
				(User canInput: FALSE)
				(switch reasonForLeaving
					(beMoreInteresting
						(ErasmusPrint 4 31 28)
						;I told you to be more interesting!  You may go now.
						)
					(knowAllPunchlines
						(ErasmusPrint 8 31 29)
						;You seem to know all of my punchlines, so... to SPELL with you!
						)
					(attackedWizard
						(ErasmusPrint 6 31 30)
						;Your thoughts give you away, but before you can DO anything...
						)
					(castMagic
						(ErasmusPrint 4 31 31)
						;Not in MY house you don't...
						)
					(noSleeping
						(ErasmusPrint 4 31 31)
						;Not in MY house you don't...
						)
					(else
						(ErasmusPrint 4 31 32)
						;Well, if you really must go...
						)
				)
			)
			(1
				(= erasmusTalking TRUE)
				(wizard hide:)
				(wizDrinking setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(2
				(Magic
					ignoreActors:
					setPri: (+ (ego priority?) 1)
					posn: (egoChair x?) (egoChair y?)
					init:
					setCycle: CycleTo 6 1 self
				)
			)
			(3
				(ego hide:)
				(egoChair setLoop: 0 setCel: 3)
				(Magic setCycle: EndLoop self)
			)
			(4
				(Bset ERASMUS_WARPOUT)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 16)
				(self cue:)
			)
			(5 (curRoom newRoom: 28))
		)
	)
)

(instance table of PicView
	(properties
		y 149
		x 152
		view vWizardTable
		cel 1
		priority 6
	)
)

(instance shelf of PicView
	(properties
		y 123
		x 201
		view vWizardTable
		cel 8
		priority 7
	)
)

(instance door1 of Actor
	(properties
		y 119
		x 153
		view vWizardTable
		illegalBits $0000
		xStep 1
	)
)

(instance door2 of Actor
	(properties
		y 119
		x 168
		view vWizardTable
		illegalBits $0000
		xStep 1
	)
)

(instance egoChair of View
	(properties
		y 151
		x 183
		view vWizardTable
		cel 3
	)
)

(instance teacup of View
	(properties
		y 123
		x 147
		view vWizardTable
		cel 5
	)
)

(instance wizBody of View
	(properties
		y 148
		x 131
		view vWizardTable
		cel 6
	)
)

(instance wizard of Actor
	(properties
		y 122
		x 129
		view vWizardTable
		loop 2
		cycleSpeed 1
	)
)

(instance wizDrinking of Actor
	(properties
		y 128
		x 136
		view vWizardTable
		loop 1
		cycleSpeed 1
	)
)

(instance fenrus of Prop
	(properties
		y 113
		x 206
		view vFenrus
		loop 4
	)
)

(instance poof of Prop
	(properties
		y 108
		x 204
		view vFenrus
		loop 3
		cel 5
		cycleSpeed 1
	)
)

(instance Magic of Prop
	(properties
		view vTeleportPink
		cycleSpeed 2
	)
)

(instance onCheese of RFeature
	(properties
		nsTop 101
		nsLeft 192
		nsBottom 108
		nsRight 197
	)
)

(instance onRat of RFeature
	(properties
		nsTop 91
		nsLeft 198
		nsBottom 114
		nsRight 210
	)
)

(instance onLWindow of RFeature
	(properties
		nsTop 88
		nsLeft 119
		nsBottom 100
		nsRight 131
	)
)

(instance onRWindow of RFeature
	(properties
		nsTop 90
		nsLeft 187
		nsBottom 111
		nsRight 197
	)
)

(instance onStairs of RFeature
	(properties
		nsTop 156
		nsLeft 161
		nsBottom 166
		nsRight 197
	)
)

(instance onTable of RFeature
	(properties
		nsTop 118
		nsLeft 138
		nsBottom 149
		nsRight 168
	)
)

(instance onCeiling of RFeature
	(properties
		nsLeft 97
		nsBottom 90
		nsRight 224
	)
)

(instance entryDelay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (client setScript: register))
		)
	)
)
