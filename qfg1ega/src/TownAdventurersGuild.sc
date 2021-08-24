;;; Sierra Script 1.0 - (do not remove this comment)
(script# 311)
(include game.sh)
(use Main)
(use Door)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm311 0
)

(local
	burningLog
	guildDoor
	wolfgangView
	wolfgangArm
	local4
	local5
	local6
	wolfgangAwake
	local8
	local9
	remainingPages
	chatWolfgang
)
(procedure (GetCloser)
	(HighPrint 311 0)
	;Get closer for a good look.
)

(procedure (Logbook &tmp page [str 200])
	(= page remainingPages)
	(if (not (Btst SIGNED_LOGBOOK)) (++ page))
	(if (ego inRect: 190 115 250 124)
		(if (< remainingPages 0)
			(HighPrint 311 1)
			;The remaining pages are blank, awaiting further tales of adventure.
			(= remainingPages 0)
			(return)
		)
		(SolvePuzzle POINTS_READLOGBOOK 4)
		(switch page
			(0
				(Format @str 311 2 @userName)
				;The writing on this page looks very recent.  It says "I, %s, have come to Spielburg to become a Hero."
				(HighPrint @str)
			)
			(1
				(Format @str 311 3
					(if (Btst SIGNED_LOGBOOK) {previous} else {last})
					;The %s entry was made several years ago.
					;It says: "Baronet Barnard von Spielburg killed a Troll near the Flying Falls on this 23 day of Octember".
				)
				(HighPrint @str)
			)
			(else 
				(HighPrint 311 4)
				;The other pages record the exploits of the great adventurers of Spielburg's past.
				;The names Wolfgang Abenteuer and Baron Stefan von Spielburg appear most often.
				(= remainingPages 2)
			)
		)
	else
		(GetCloser)
	)
)

(procedure (NearDesk)
	(if (not (ego inRect: 190 115 250 124))
		(GetCloser)
	)
	(ego inRect: 190 115 250 124)
)

(procedure (ReadNoticeBoard)
	(if (ego inRect: 100 110 167 126)
		(SolvePuzzle POINTS_READNOTICEBOARD 6)
		(curRoom newRoom: 318)
	else
		(GetCloser)
	)
)

(procedure (LookMaster)
	(HighPrint 311 5)
	;It looks like this old Guild Master doesn't do too much adventuring any more.
	;Still, he looks like he was plenty tough in his day, and he probably has many a tale to tell.
)

(procedure (LookFireplace)
	(HighPrint 311 6)
	;The fire blazing cheerily in the fireplace helps warm the Guild Master's old bones.
)

(procedure (LookMoose)
	(HighPrint 311 7)
	;The plaque under the Moose says: "Courtesy of Sierra Online Prop Dept."
)

(procedure (LookSaurus)
	(HighPrint 311 8)
	;You never saw a purple Saurus before you came to Spielburg, but it looks like a really stupid monster.
	;The plaque reads:  "Saurus slain by Hans Halfwitten".
)

(procedure (LookTroll)
	(HighPrint 311 9)
	;It looks like it must have been a particularly nasty Troll.
	;You wouldn't want to meet him in a dark forest.  The plaque reads:  "Troll slain by Wolfgang Abenteuer".
)

(procedure (LookGryphon)
	(HighPrint 311 10)
	;This crossbreed of eagle and lion could have torn a man apart when it was alive.  The plaque reads: "Gryphon slain by Wolfgang Abenteuer".
)

(procedure (LookDragon)
	(HighPrint 311 11)
	;Even in death, this monster remains awesome.  The plaque reads: "Dragon slain by Baron Stefan von Spielburg".
)

(procedure (LookCheetaur)
	(HighPrint 311 12)
	;The head is like a panther's, but with a strong human-like quality.  It is still rather frightening.
	;The plaque reads:  "Cheetaur slain by Wolfgang Abenteuer".
)

(procedure (LookAntwerp)
	(HighPrint 311 13)
	;This is certainly a weird one! You've never seen anything quite like it.  The plaque reads: "Antwerp slain by Two Guys From Andromeda".
)

(procedure (LookAround)
	(HighPrint 311 14)
	;This Adventurer's Guild Hall reminds you of the one in your home town.
	;The traditional Moose head and other stuffed monsters (Saurus, Troll, Gryphon, Dragon, Cheetaur, and the terrible Antwerp) adorn the walls.
	(HighPrint 311 15)
	;You see the registration book on the desk and the bulletin board full of job listings.
	;The man seated by the fire must be the Guild Master.  He is snoring.
)

(instance rm311 of Room
	(properties
		picture 311
		style WIPELEFT
	)
	
	(method (init)
		(Load VIEW vAdventurerGuild)
		(Load VIEW vWolfgang)
		(super init:)
		(mouseDownHandler add: self)
		(self
			setFeatures:
				onDragon
				onCheetaur
				onTroll
				onGryphon
				onAntwerp
				onSaurus
				onMoose
				onGuildMaster
				onFireplace
				onLog
				onQuestBoard
		)
		(self setLocales: TOWN)
		(StatusLine enable:)
		(master init:)
		(NormalEgo)
		(ego loop: 1 posn: 276 148 init:)
		(if (== prevRoomNum 318) (ego posn: 133 118))
		((= burningLog (Prop new:))
			view: vAdventurerGuild
			loop: 1
			posn: 11 160
			init:
			setCycle: Forward
			cycleSpeed: 1
			startUpd:
		)
		((= guildDoor (Door new:))
			view: vAdventurerGuild
			loop: 0
			posn: 315 158
			doorControl: cYELLOW
			entranceTo: 310
			init:
			ignoreActors:
			setPri: 11
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 0
			posn: 135 73
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 1
			posn: 209 92
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 2
			posn: 194 87
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 3
			posn: 244 67
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 4
			posn: 25 81
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 5
			posn: 204 66
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 6
			posn: 110 66
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 7
			posn: 163 70
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 8
			posn: 74 69
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vAdventurerGuild
			loop: 2
			cel: 9
			posn: 302 83
			init:
			stopUpd:
			addToPic:
		)
		(if (not (Btst fBeenIn311)) (LookAround))
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn311)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 3])
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'get')
						(HighPrint 311 16)
						;There is nothing here that you need, and the Guild Master may be more aware than he seems.
					)
					((or (Said 'turn/page') (Said 'page<preceding'))
						(++ remainingPages)
						(Logbook)
					)
					((Said '/page<next')
						(-- remainingPages)
						(Logbook)
					)
					((Said '/page<first')
						(= remainingPages 2)
						(Logbook)
					)
					((Said '/page<last')
						(= remainingPages 0)
						(Logbook)
					)
					(
						(or
							(Said 'look<in/desk,drawer')
							(Said 'open/desk,drawer')
						)
						(if (NearDesk)
							(HighPrint 311 17)
							;The desk drawers are empty.
						)
					)
					((Said 'close/desk')
						(HighPrint 311 18)
						;There is no way to close the desk.
					)
					((Said 'look,read>')
						(cond 
							((Said '/registration,register,log,book,page') (Logbook))
							((Said '/desk') (if (NearDesk)
									(HighPrint 311 19)
									;On the desk are an old but ornate leather-bound book and a quill pen in the ink bottle.
									)
							)
							((Said '/feather,pen,ink,bottle')
								(HighPrint 311 20)
								;You see an ordinary pen and ink.
							)
							((Said '/message,quest,(board[<bulletin,quest])') (ReadNoticeBoard))
							((Said '[<at,around][/!*,room,building,hall,club]') (LookAround))
							((Said '/moose,(head<moose)') (LookMoose))
							((Said '/saurus,(head<saurus)') (LookSaurus))
							((Said '/troll,(head<troll)') (LookTroll))
							((Said '/griffin,(head<griffin)') (LookGryphon))
							((Said '/dragon,(head<dragon)') (LookDragon))
							((Said '/cheetaur,(head<cheetaur)') (LookCheetaur))
							((Said '/antwerp,(head<antwerp)') (LookAntwerp))
							((Said '/monster,trophy,head')
								(HighPrint 311 21)
								;On the walls hang dusty, moth-eaten monster heads.  You recognize the purple Saurus, the Troll, the Gryphon, and the Dragon.
								(HighPrint 311 22)
								;You figure that the black head must belong to the Cheetaur, and you have no idea what the four-eyed creature might be.
								;You are also not quite sure why the Moose head is considered traditional, but every Guild Hall seems to have one.
								)
							((Said '/desk')
								(HighPrint 311 23)
								;The desk holds a pen, an inkwell, and the Adventurer's Guild Registration and Log Book.
								)
							(
							(Said '/abenteuer,master,man,adventurer,guildmaster') (LookMaster))
							((Said '/fire,chimney') (LookFireplace))
						)
					)
					(
						(or
							(Said 'sign,register[/name,book,register,log]')
							(Said 'write,enter/name,quest[/book,register,log]')
						)
						(cond 
							((Btst SIGNED_LOGBOOK)
								(HighPrint 311 24)
								;But you've already done that!
							)
							((ego inRect: 190 115 250 124)
								(HighPrint 311 25)
								;You sign your name into the Adventurer's Log Book with a flourish.
								(SolvePuzzle POINTS_SIGNLOGBOOK 1)
								(Bset SIGNED_LOGBOOK)
							)
							(else
								(NotClose)
							)
						)
					)
				)
			)
		)
	)
)

(instance master of Prop
	(properties
		view vWolfgang
	)
	
	(method (init)
		(super init:)
		(self
			posn: 76 137
			setPri: 12
			ignoreActors:
			startUpd:
			setScript: masterScript
		)
		((= wolfgangView (View new:))
			view: vWolfgang
			loop: 0
			cel: 0
			posn: 82 153
			init:
			stopUpd:
		)
		((= wolfgangArm (Prop new:))
			view: vWolfgang
			loop: 3
			cel: 0
			posn: 72 130
			init:
			cycleSpeed: 1
			setPri: 12
			stopUpd:
			hide:
		)
	)
	
	(method (doit)
		(cond 
			((> local9 1) (-- local9))
			((== local9 1) (= local9 0) (if wolfgangAwake (= local8 1)))
		)
		(if (== (master loop?) 2)
			(cond 
				((< (ego x?) 100) (if (== (master cel?) 1) (master cel: 0)))
				((== (master cel?) 0) (master cel: 1))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance masterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= wolfgangAwake 0)
				(= local4 5)
				(master loop: 1 cel: 0 cycleSpeed: 0)
				(= cycles 3)
			)
			(1
				(master cel: 1)
				(= cycles 3)
			)
			(2
				(master cel: 2)
				(= cycles 3)
			)
			(3
				(master cel: 3)
				(= cycles 3)
			)
			(4
				(master cel: 1)
				(= cycles 1)
			)
			(5
				(master cel: 0)
				(-- local4)
				(= cycles 1)
			)
			(6
				(if (> local4 0)
					(self changeState: 4)
				else
					(self changeState: 0)
				)
			)
			(7
				(HighPrint 311 65)
				;"ZZZZZZZZZZZZZZZZ."
				(master
					loop: 2
					cel: (if (> (ego x?) 100) 1 else 0)
					posn: 80 134
				)
				(wolfgangView cel: 1)
				(wolfgangArm show:)
				(= cycles 5)
			)
			(8
				(if local6
					(HighPrint 297 (Random 21 25)
						)
				else
					(= local6 1)
					(HighPrint 311 66)
					;"Ach! I was so busy I didn't notice you come in. Welcome, welcome!
					;It is so seldom that we have new adventurers here. Most people think this valley is cursed!"
				)
				(= local9 100)
				(= local5 (Random 1 4))
				(= wolfgangAwake 1)
				(= cycles 2)
			)
			(9
				(wolfgangArm setCycle: EndLoop self)
			)
			(10
				(wolfgangArm setCycle: BegLoop self)
			)
			(11
				(if (> local5 0)
					(-- local5)
					(self changeState: 9)
				else
					(= cycles (Random 30 60))
				)
			)
			(12
				(= local5 (Random 1 4))
				(if local8 (self cue:) else (self changeState: 9))
			)
			(13
				(= local8 0)
				(= wolfgangAwake 0)
				(= local9 0)
				(master loop: 4 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(14 (self changeState: 0))
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(Said
							'awaken[<up]/adventurer,master,abenteuer,man,guildmaster'
						)
						(if wolfgangAwake
							(HighPrint 311 26)
							;He already is.
						else
							(self changeState: 7)
						)
					)
					((Said 'chat/adventurer,master,abenteuer,man')
						(if (ego inRect: 0 132 106 195)
							(= local9 100)
							(if wolfgangAwake
								(HighPrint 311 27)
								;"Oh, my young friend!  What shall we talk about?  I have so many stories.  Just ask me about something."
							else
								(self changeState: 7)
							)
						else
							(NotClose)
						)
					)
					((Said 'ask>')
						(if (ego inRect: 0 132 106 195)
							(= local9 100)
							(cond 
								(wolfgangAwake
									(= chatWolfgang TRUE)
									(cond 
										((Said '//curse') 
											(HighPrint 311 28)
											;"Ja.  What with the baron losing his son and daughter, all the monsters,
											;Baba Yaga, and now the brigands, it has been one thing after another."
											)
										((Said '//baron')
											(HighPrint 311 29)
											;"Baron von Spielburg was once a brave protector of our valley.  We used to adventure together when we were younger.
											;No brigand or monster would dare show his face here if the Baron had not angered Baba Yaga.
											;Now it is said that he goes nowhere and sees no one."
											)
										((Said '//barnard,barnard,boy')
											(HighPrint 311 30)
											;"He was a hero and worthy of the name Barnard von Spielburg.
											;He rode off to hunt one morning five years ago, and his horse returned with large and deep claw marks.
											;No sign of the Baronet's body was ever found."
											)
										((Said '//elsa,daughter,girl')
											(HighPrint 311 31)
											;"Elsa was a beautiful eight-year-old child with blonde hair and sky-blue eyes.  She was the Baron's pride and joy."
											(HighPrint 311 32)
											;"Ten years ago she was carried off by something which came over the wall and flew off with her.
											;The search for her lasted many years, but at last everyone gave up."
											(HighPrint 311 33)
											;"Everyone, that is, except for the Baron's Jester, Yorick."
											)
										((Said '//jester,yorick')
											(HighPrint 311 34)
											;"Yorick was a funny little man, but brave.  He swore he would spend his life searching for the Baron's Elsa."
											)
										((Said '//baba')
											(HighPrint 311 35)
											;"She is the center of our problems, I think.  Baba Yaga is a powerful Ogress.
											;Baron von Spielburg tried to force her to leave this valley, but she cursed him."
											(HighPrint 311 36)
											;"Now the Baron has lost everything but his land, and I don't know how long he will keep that.
											;What our valley needs is a Hero."
											)
										((Said '//adventure,adventurer,adventuring')
											(HighPrint 311 37)
											;"Did I ever tell you about the days when Schultz and I rid this valley of Antwerps?
											;Ja, we were real adventurers then, and this was a true Guild Hall.
											;Now we are just old men, and this is just a place to tell old stories."
											)
										((Said '//meisterson')
											(HighPrint 311 38)
											;"Schultz and I have been friends for a long time.  He too has been a real adventurer.
											;Now, he is just the Sheriff of the town."
											)
										((Said '//thief,(club<thief)')
											(HighPrint 311 39)
											;"Even a thief can be a hero sometimes, as long as uses his skills only for good purposes.
											;But most thieves are far from being heroes."
											)
										((Said '//hall,club')
											(HighPrint 311 40)
											;"This is, of course, where an adventurer can find out where there is a need for someone brave and courageous.
											;There are jobs on the bulletin board over there."
											(HighPrint 311 41)
											;"It is also a good place to talk about adventures on a cold afternoon.
											;We used to play cards here once a week, as well, but there are too few adventurers in Spielburg anymore.
											;They all died at the hands of monsters or brigands, or they just became too old."
											)
										((Said '//board,bulletin,notice,job,labor')
											(HighPrint 311 40)
											;"This is, of course, where an adventurer can find out where there is a need for someone brave and courageous.
											;There are jobs on the bulletin board over there."
											)
										((Said '//monster,head')
											(HighPrint 311 42)
											;"You can see some of the types of monsters that live around here if you look at our walls."
											)
										((Said '//bandit')
											(HighPrint 311 43)
											;"There is a reward for anyone who can stop the brigands by capturing or killing their leaders.
											;The information is on the bulletin board."
											)
										((Said '//saurus')
											(HighPrint 311 44)
											;"Oh, those are nothing.  Anyone can kill a Saurus."
											)
										((Said '//troll')
											(HighPrint 311 45)
											;"It's been a while since Schultz and I killed the one on the wall.
											;It is fortunate that few Trolls remain; they're deadly."
											)
										((Said '//cheetaur')
											(HighPrint 311 46)
											;"Watch out for Cheetaurs.  I bear the scars of my last fight with one to this day."
											)
										((Said '//griffin')
											(HighPrint 311 47)
											;"I remember the day I killed one.  It was the biggest one I had ever seen.
											;It put up a tough fight, but I was tougher.  You can see the result on the wall."
											)
										((Said '//dragon')
											(HighPrint 311 48)
											;"One day, years ago, a pair of dragons tried to take over our valley.  We adventurers rode out to meet them."
											(HighPrint 311 49)
											;"I can still see Stefan von Spielburg charging forward on his black horse.
											;He slew the one whose head adorns the wall above our fireplace.  The other one flew off."
											(HighPrint 311 50)
											;"People say that they can sometimes see that other dragon flying high overhead, but it has never dared attack us again."
											)
										((Said '//antwerp')
											(HighPrint 311 51)
											;"One year, this valley was overrun by those odd and terrible monsters.
											;Schultz and I fought long and hard to eliminate them completely.
											;We might have failed even so, had it not been for those two peculiar tourists who came to our aid."
											)
										((Said '//moose')
											(HighPrint 311 52)
											;"That was the most vicious moose I ever ran into.  Nearly bit my nose off!"
											)
										((Said '//tourist')
											(HighPrint 311 53)
											;"They certainly were strange."
											)
										((Said '//andromeda')
											(HighPrint 311 54)
											;"That's where the tourists said they came from.  Must be some place up North -- I sure never heard of it."
											)
										((Said '//halfwitten')
											(HighPrint 311 55)
											;"Not much of an adventurer, but he did kill one Saurus before he made the mistake of tangling with an Ogre."
											)
										((Said '//ogre')
											(HighPrint 311 56)
											;"Ogres are a lot like Goons, but even meaner.  Not as bright, though."
											)
										((Said '//goon,otto,bouncer')
											(HighPrint 311 57)
											;"A Goon is a lot like an Ogre, not as tough, but a little smarter.  We have two Goons in town, Otto and Crusher."
											)
										((Said '//abenteuer,master,name')
											(HighPrint 311 58)
											;"I am Wolfgang Abenteuer, Guild Master of this hall."
											)
										((Said '//hero,fame')
											(HighPrint 311 59)
											;"A real hero is someone who did not start out strong or powerful, but who uses his courage, brains,
											;and skills to become the best he can be."
											)
										(
											(or
												(Said '//ring,warlock,spell,leader')
												(Said '//component[<spell,about]')
											)
											(HighPrint 311 60)
											;"I'm sorry.  I only know what's written on the poster."
										)
										((Said '//castle')
											(HighPrint 311 61)
											;"The castle is just north of the Healer's house."
											)
										((Said '//heal,house')
											(HighPrint 311 62)
											;"The Healer's house is just north of the crossroads outside of town."
											)
										((Said '//*')
											(= chatWolfgang 0)
											(HighPrint 311 63)
											;"You know, that reminds me very little of the time old Schultz and I......."
											(HighPrint 311 64)
											;"Wait a minute. It'll come to me."
											(= local8 1)
										)
									)
									(if chatWolfgang (SolvePuzzle POINTS_TALKTOGUILDMASTER 1))
								)
								((Said '//*') (masterScript changeState: 7))
							)
						else
							(NotClose)
						)
					)
				)
			)
		)
	)
)

(instance onDragon of RFeature
	(properties
		nsTop 55
		nsBottom 80
		nsRight 50
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onDragon event shiftDown) (LookDragon))
		)
	)
)

(instance onCheetaur of RFeature
	(properties
		nsTop 55
		nsLeft 65
		nsBottom 70
		nsRight 80
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onCheetaur event shiftDown) (LookCheetaur))
		)
	)
)

(instance onTroll of RFeature
	(properties
		nsTop 40
		nsLeft 98
		nsBottom 65
		nsRight 122
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onTroll event shiftDown) (LookTroll))
		)
	)
)

(instance onGryphon of RFeature
	(properties
		nsTop 40
		nsLeft 145
		nsBottom 68
		nsRight 180
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onGryphon event shiftDown) (LookGryphon))
		)
	)
)

(instance onAntwerp of RFeature
	(properties
		nsTop 40
		nsLeft 192
		nsBottom 65
		nsRight 215
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onAntwerp event shiftDown) (LookAntwerp))
		)
	)
)

(instance onSaurus of RFeature
	(properties
		nsTop 54
		nsLeft 221
		nsBottom 67
		nsRight 245
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onSaurus event shiftDown) (LookSaurus))
		)
	)
)

(instance onMoose of RFeature
	(properties
		nsTop 57
		nsLeft 279
		nsBottom 85
		nsRight 314
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onMoose event shiftDown) (LookMoose))
		)
	)
)

(instance onGuildMaster of RFeature
	(properties
		nsTop 124
		nsLeft 62
		nsBottom 150
		nsRight 80
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onGuildMaster event shiftDown) (LookMaster))
		)
	)
)

(instance onFireplace of RFeature
	(properties
		nsTop 119
		nsLeft 1
		nsBottom 155
		nsRight 28
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onFireplace event shiftDown) (LookFireplace))
		)
	)
)

(instance onLog of RFeature
	(properties
		nsTop 81
		nsLeft 197
		nsBottom 94
		nsRight 219
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onLog event shiftDown) (Logbook))
		)
	)
)

(instance onQuestBoard of RFeature
	(properties
		nsTop 73
		nsLeft 106
		nsBottom 92
		nsRight 162
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onQuestBoard event shiftDown) (ReadNoticeBoard))
		)
	)
)
