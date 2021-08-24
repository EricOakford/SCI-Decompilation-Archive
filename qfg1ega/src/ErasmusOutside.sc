;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include game.sh)
(use Main)
(use Intrface)
(use String)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm29 0
)

(local
	local0
	wornOut
	passedOut
	local3
	passedTest
	preTeleport
	askThiefPassword
	gargoyleCued
	questionAsked
	askName
	local10
)

(enum
	beatIt
	wrongAnswer
	dontAskMeQuestions
	noThieves
	noSleeping
	notNice
)

(procedure (GargoyleSpeaks seconds theWidth &tmp [str 400])
	(Format @str &rest)
	(Print
		@str
		#at -1 12
		#mode teJustCenter
		#width theWidth
		#dispose
		#time seconds
	)
)

(procedure (WrongAnswer)
	(= preTeleport wrongAnswer)
	(= gargoyleCued TRUE)
	(Bset fWrongAnswer)
)

(procedure (ThatsNotNice)
	(= preTeleport notNice)
	(= gargoyleCued TRUE)
)

(instance Magic of Prop
	(properties
		view vTeleportPink
		cycleSpeed 2
	)
)

(instance leftDoor of Prop
	(properties
		view vWizardDoor
	)
)

(instance rightDoor of Prop
	(properties
		view vWizardDoor
		loop 1
	)
)

(instance gargoyle of Prop
	(properties
		view vWizardDoor
		loop 2
	)
)

(instance gargoyleHead of Prop
	(properties
		view vWizardDoor
		loop 5
		cel 1
		cycleSpeed 1
	)
)

(instance rm29 of Room
	(properties
		picture 29
		style DISSOLVE
		horizon 157
		north 30
	)
	
	(method (init)
		(Load VIEW vWizardDoor)
		(Load VIEW vTeleportPink)
		(Load TEXT 257)
		(super init:)
		(keyDownHandler add: self)
		(StatusLine enable:)
		(ChangeGait MOVE_WALK FALSE)
		(gargoyle cycleSpeed: 2 posn: 155 88 init: stopUpd:)
		(gargoyleHead
			ignoreActors:
			posn: 156 75
			setPri: 15
			setCycle: Forward
			init:
			hide:
			stopUpd:
		)
		(leftDoor
			cel: (if (== prevRoomNum 30) 2 else 0)
			posn: 144 156
			init:
			stopUpd:
		)
		(rightDoor
			cel: (if (== prevRoomNum 30) 2 else 0)
			posn: 167 156
			init:
			stopUpd:
		)
		(self setScript: walkIn)
	)
	
	(method (doit)
		(cond 
			(gargoyleCued
				(= gargoyleCued FALSE)
				(gargoyle setScript: 0)
				(self setScript: teleportOut)
			)
			((and local0 (== (ego edgeHit?) SOUTH))
				(curRoom newRoom: 28)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn29)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell i [str 10])
		(if
			(and
				4
				(User canInput?)
				(not (event claimed?))
				local10
				(or
					(== (event message?) (User echo?))
					(and
						(<= SPACEBAR (event message?))
						(<= (event message?) 127)
					)
				)
			)
			(event claimed: TRUE)
			(if (User getInput: event)
				(cond 
					((StrFind (User inputLineAddr?) {look})
						(event type: saidEvent)
						(event claimed: FALSE)
						(Parse (User inputLineAddr?) event)
					)
					(
						(or
							(StrFind (User inputLineAddr?) {ask})
							(StrFind (User inputLineAddr?) {where})
						)
						(= preTeleport dontAskMeQuestions)
						(= gargoyleCued TRUE)
						(Bset fWrongAnswer)
					)
					(else
						(switch askName
							(1
								(if (StrFind (User inputLineAddr?) @userName)
									(askQuestions changeState: 0)
								else
									(WrongAnswer)
								)
							)
							(2
								(switch questionAsked
									(1
										(if
											(or
												(StrFind (User inputLineAddr?) {purple})
												(StrFind (User inputLineAddr?) {pink})
											)
											(askQuestions changeState: 0)
										else
											(WrongAnswer)
										)
									)
									(2
										(if
											(or
												(StrFind (User inputLineAddr?) {hero})
												(StrFind (User inputLineAddr?) {glory})
												(StrFind (User inputLineAddr?) {wizard})
											)
											(askQuestions changeState: 0)
										else
											(WrongAnswer)
										)
									)
									(3
										(if
											(or
												(StrFind (User inputLineAddr?) {fenrus})
												(StrFind (User inputLineAddr?) {erasmus})
												(StrFind (User inputLineAddr?) {wizard})
											)
											(askQuestions changeState: 0)
										else
											(WrongAnswer)
										)
									)
								)
							)
							(3
								(if (Btst fAttackedErasmus)
									(switch questionAsked
										(1
											(if
												(or
													(StrFind (User inputLineAddr?) {african})
													(StrFind (User inputLineAddr?) {european})
												)
												(askQuestions changeState: 0)
											else
												(WrongAnswer)
											)
										)
										(2
											(if
												(or
													(StrFind (User inputLineAddr?) {42})
													(StrFind (User inputLineAddr?) {forty two})
													(StrFind (User inputLineAddr?) {forty-two})
													(StrFind (User inputLineAddr?) {cherr})
												)
												(askQuestions changeState: 0)
											else
												(WrongAnswer)
											)
										)
										(3
											(if (StrFind (User inputLineAddr?) {yes})
												(askQuestions changeState: 0)
											else
												(WrongAnswer)
											)
										)
										(4
											(if (StrFind (User inputLineAddr?) {maybe})
												(askQuestions changeState: 0)
											else
												(WrongAnswer)
											)
										)
									)
								else
									(switch questionAsked
										(1
											(if (StrFind (User inputLineAddr?) {stefan})
												(askQuestions changeState: 0)
											else
												(WrongAnswer)
											)
										)
										(2
											(if (StrFind (User inputLineAddr?) {erana})
												(askQuestions changeState: 0)
											else
												(WrongAnswer)
											)
										)
										(3
											(for ((= i 0)) (<= i 6) ((++ i))
												(if
													(StrFind
														(User inputLineAddr?)
														(Format @str 257 i)
													)
													(break)
												)
											)
											(if (<= i 6)
												(= preTeleport noThieves)
												(= gargoyleCued TRUE)
												(Bset fWrongAnswer)
											else
												(= askThiefPassword TRUE)
												(askQuestions changeState: 0)
											)
										)
									)
								)
							)
						)
					)
				)
			)
		)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,house,mansion,north]')
								(HighPrint 29 0)
								;You see the purple home of someone who must enjoy its precarious perch upon the peak.
							)
							((Said '/gargoyle,creature')
								(HighPrint 29 1)
								;The stone creature looks like it's bored.  Wouldn't you be?
							)
							((Said '/cliff,boulder')
								(HighPrint 29 2)
								;The cliff walls are steep, and it's a long way to the snow fields below.
							)
							((Said '/hill,peak,east')
								(HighPrint 29 3)
								;You can see the mist flowing around the edges of the Wolf's Bane Peaks.
							)
							((Said '/ice')
								(HighPrint 29 4)
								;Below you, the snow glistens.
							)
							((or (Said '<up') (Said '/sky'))
								(HighPrint 29 5)
								;It is cloudless. It seems that you are above the clouds.
							)
							((or (Said '<down') (Said '/ground,path,south'))
								(HighPrint 29 6)
								;Looking down and to the south, you see the tortuous path you climbed to get here.
								;You'd hate to have to do that again.
							)
							((Said '/castle,west,forest')
								(HighPrint 29 7)
								;Looking below you to the west, you can see the Baron's Castle surrounded by the forest.
							)
							((Said '/window')
								(HighPrint 29 8)
								;From outside, you can see that the interior of the house is curiously furnished.
							)
							((Said '/door')
								(if passedTest
									(HighPrint 29 9)
									;It's about time the gargoyle opened the door for you.
								else
									(HighPrint 29 10)
									;You'd like to look at it more closely, if only the gargoyle would let you.
								)
							)
						)
					)
					((Said 'throw,attack,kill,beat')
						(ThatsNotNice)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 29 11)
									;You detect a strange, magical aura in this place.
								)
							)
							(DAZZLE
								(if (CastSpell spell)
									(ThatsNotNice)
								)
							)
							(FLAMEDART
								(if (CastSpell spell)
									(ThatsNotNice)
								)
							)
							(OPEN
								(if (CastSpell spell)
									(if passedTest
										(HighPrint 29 12)
										;The door is already open.
										else
										(ThatsNotNice)
									)
								)
							)
						)
					)
					((or (Said 'nap[/!*]') (Said 'go[<to]/nap'))
						(= preTeleport noSleeping)
						(= gargoyleCued TRUE)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance walkIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(UseStamina 30)
				(cond 
					((< [egoStats STAMINA] 20)
						(= passedOut TRUE)
					)
					((< [egoStats STAMINA] 60)
						(= wornOut TRUE)
					)
				)
				(= local10 1)
				(cond 
					(passedOut
						(ego
							cycleSpeed: 2
							moveSpeed: 2
							view: vEgoTired
							setLoop: 2
							posn: 142 233
							setMotion: MoveTo 148 183 self
						)
					)
					(wornOut
						(ego
							cycleSpeed: 1
							moveSpeed: 1
							view: vEgoTired
							setLoop: 2
							posn: 142 233
							setMotion: MoveTo 148 183 self
						)
					)
					(else
						(ego posn: 135 213 setMotion: MoveTo 152 180 self)
					)
				)
				(ego init:)
			)
			(1
				(cond 
					(passedOut
						(ego
							setLoop: 1
							cel: 0
							setCycle: EndLoop
						)
						(= cycles 30)
					)
					(wornOut
						(ego
							cycleSpeed: 2
							moveSpeed: 2
							setLoop: 0
							cel: 0
							setCycle: CycleTo 5 1
						)
						(= cycles 20)
					)
					(else
						(self cue:)
					)
				)
			)
			(2
				(cond 
					(passedOut
						(ego setCycle: BegLoop self)
					)
					(wornOut
						(ego setCycle: EndLoop self)
					)
					(else
						(self cue:)
					)
				)
			)
			(3
				(if passedOut
					(ego setLoop: 0 cel: 7 forceUpd:)
					(= cycles 5)
				else
					(self cue:)
				)
			)
			(4
				(= local0 1)
				(cond 
					((not (Btst fBeenIn29))
						(HighPrint 29 13)
						;You feel as though you have just scaled the Matterhorn in full armor.  What a climb!
						(HighPrint 29 14)
						;After you finally catch your breath, you see that you have reached the rather eccentric-looking house that you saw from below.
						(HighPrint 29 15)
						;There is an ugly gargoyle above the entrance.
						(self cue:)
					)
					(passedOut
						(HighPrint 29 16)
						;Your body can't take too much more of this kind of mountaineering.  At this point, you envy the gargoyle his job.
						(self cue:)
					)
					(wornOut
						(HighPrint 29 17)
						;No matter how strong you feel at the bottom of the mountain, you're always more exhausted than the last time you reached the top.
						(self cue:)
					)
					(else
						(HighPrint 29 18)
						;The climb seems much longer this time, but you made it!
						(self cue:)
					)
				)
			)
			(5
				(HandsOff)
				(gargoyle cel: 1 forceUpd:)
				(= cycles 5)
			)
			(6
				(gargoyleHead show: startUpd:)
				(cond 
					(Night
						(GargoyleSpeaks 4 210 29 19)
						;"DO NOT DISTURB THE WIZARD AT REST!"
					)
					(passedOut
						(GargoyleSpeaks 4 160 29 20)
						;"AND YOU WANT TO BE A HERO?!"
					)
					(wornOut
						(GargoyleSpeaks 4 210 29 21)
						;"YOU DON'T GIVE UP EASILY, DO YOU?"
					)
					((Btst fBeenIn29)
						(GargoyleSpeaks 4 140 29 22)
						;"OH!  YOU AGAIN!"
					)
					(else
						(GargoyleSpeaks 4 110 29 23)
						;"STAND FAST!"
					)
				)
				(= seconds 4)
			)
			(7
				(cond 
					(Night
						(= gargoyleCued TRUE)
						(gargoyleHead stopUpd: hide:)
					)
					(passedOut
						(GargoyleSpeaks 4 230 29 24)
						;"CAN YOU HEAR ME?
						;YOU HAVE THREE QUESTIONS."
					)
					((Btst fMetGargoyle)
						(GargoyleSpeaks 4 230 29 25)
						;"HERE COME YOUR QUESTIONS:"
					)
					(else (Bset fMetGargoyle)
						(GargoyleSpeaks 4 280 29 26)
						;"HE WHO WOULD THE WIZARD SEE
						;FIRST MUST ANSWER QUESTIONS THREE."
					)
				)
				(= seconds 4)
			)
			(8
				(gargoyleHead hide: stopUpd:)
				(= cycles 5)
			)
			(9
				(rm29 setScript: askQuestions)
			)
		)
	)
)

(instance teleportOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch preTeleport
					(beatIt
						(GargoyleSpeaks 4 100 29 27)
						;"BEAT IT, BUB!"
						)
					(wrongAnswer
						(GargoyleSpeaks 4 60 29 28)
						;"WRONG!"
					)
					(dontAskMeQuestions
						(GargoyleSpeaks 4 140 29 29)
						;ASKING QUESTIONS
						;IS *MY* JOB, BUB!
					)
					(noThieves
						(GargoyleSpeaks 4 180 29 30)
						;"NO THIEVES ALLOWED!"
					)
					(noSleeping
						(GargoyleSpeaks 4 140 29 31)
						;The Wizard is not running an inn.
					)
					(notNice
						(GargoyleSpeaks 4 180 29 32)
						;"THAT'S NOT NICE!"
					)
				)
				(HandsOff)
				(gargoyleHead show: startUpd:)
				(= seconds 4)
			)
			(1
				(if 2 (gargoyleHead hide: stopUpd:))
				(gargoyle setLoop: 4 setCycle: EndLoop self)
			)
			(2
				(gargoyle cel: 0 setCycle: EndLoop self)
			)
			(3
				(gargoyle setLoop: 3 cel: 0 setCycle: Forward)
				(Magic
					ignoreActors:
					setPri: (+ (ego priority?) 1)
					posn:
						(if
						(and (== (ego view?) 515) (== (ego loop?) 0))
							(+ (ego x?) 5)
						else
							(ego x?)
						)
						(ego y?)
					init:
					setCycle: CycleTo 6 1 self
				)
			)
			(4
				(ego hide:)
				(Magic setCycle: EndLoop self)
			)
			(5
				(Bset fErasmusWarpOut)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISOUT))
				(self cue:)
			)
			(6
				(curRoom newRoom: 28)
			)
		)
	)
)

(instance askQuestions of Script
	(method (doit)
		(if (and (== state 1) (< (ego y?) 175))
			(= gargoyleCued TRUE)
		)
		(if local3
			(= local3 0)
			(rm29 setScript: letHimIn)
			(gargoyle setScript: 0)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gargoyleHead show: startUpd:)
				(switch (++ askName)
					(1
						(GargoyleSpeaks 4 170 29 33)
						;"WHAT IS YOUR NAME?"
						(NormalEgo)
						(ego loop: 3)
					)
					(2
						(switch (Random 1 3)
							(1
								(= questionAsked 1)
								(GargoyleSpeaks 4 260 29 34)
								;"WHAT IS YOUR FAVORITE COLOR?"
							)
							(2
								(= questionAsked 2)
								(GargoyleSpeaks 4 160 29 35)
								;"WHAT IS YOUR QUEST?"
							)
							(3
								(= questionAsked 3)
								(GargoyleSpeaks 4 180 29 36)
								;"WHO DO YOU SEEK HERE?"
							)
						)
					)
					(3
						(if (Btst fAttackedErasmus)
							(switch (Random 1 4)
								(1
									(= questionAsked 1)
									(GargoyleSpeaks 4 230 29 37)
									;"WHAT IS THE MEAN AIRSPEED
									;OF AN UNLADEN SWALLOW?"
								)
								(2
									(= questionAsked 2)
									(GargoyleSpeaks 4 260 29 38)
									;"WHAT IS THE MEANING OF LIFE,
									;THE UNIVERSE, AND EVERYTHING?"
								)
								(3
									(= questionAsked 3)
									(GargoyleSpeaks 4 230 29 39)
									;"IS IT FURTHER TO SHAPEIR,
									;OR BY CAMEL?"
								)
								(4
									(= questionAsked 4)
									(GargoyleSpeaks 4 240 29 40)
									;"IS THIS A YES OR NO QUESTION?"
								)
							)
						else
							(switch (Random 1 3)
								(1
									(= questionAsked 1)
									(GargoyleSpeaks 4 280 29 41)
									;"WHAT IS THE BARON'S FIRST NAME?"
								)
								(2
									(= questionAsked 2)
									(GargoyleSpeaks 4 280 29 42)
									;"WHOSE SPELL PROTECTS THE TOWN?"
								)
								(3
									(= questionAsked 3)
									(GargoyleSpeaks 4 280 29 43)
									;"WHAT IS THE THIEVES' PASSWORD?"
								)
							)
						)
					)
					(else  (= local3 1))
				)
				(= seconds 4)
			)
			(1
				(HandsOn)
				(gargoyleHead hide: stopUpd:)
			)
		)
	)
)

(instance letHimIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gargoyleHead show: startUpd:)
				(= local10 0)
				(if askThiefPassword
					(GargoyleSpeaks 3 240 29 44)
					;"GOOD.  YOU'RE NOT A THIEF!"
				else
					(GargoyleSpeaks 3 140 29 45)
					;THAT IS CORRECT.
				)
				(= seconds 3)
			)
			(1
				(GargoyleSpeaks 4 230 29 46)
				;THE WIZARD WILL NOW SEE YOU.
				(= seconds 4)
			)
			(2
				(GargoyleSpeaks 4 220 29 47)
				;GO DIRECTLY UP TO THE TOWER.
				;DO NOT DALLY!
				(= seconds 4)
			)
			(3
				(gargoyleHead hide: stopUpd:)
				(gargoyle setLoop: 3 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(4
				(= passedTest TRUE)
				(leftDoor setCycle: EndLoop)
				(rightDoor setCycle: EndLoop self)
			)
			(5
				(leftDoor ignoreActors: stopUpd:)
				(rightDoor ignoreActors: stopUpd:)
				(HandsOn)
				(NormalEgo)
				(ego loop: 3)
				(gargoyle setLoop: 2 setCel: 0 stopUpd:)
			)
		)
	)
)
