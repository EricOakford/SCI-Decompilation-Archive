;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use Intrface)
(use String)
(use LoadMany)
(use DCIcon)
(use Chase)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm21 0
	baba 1
	babaHead 2
	bat 3
	spider 4
	TP 5
	noBringDeath 6
	babaTalk 7
	teleport 8
	endGame 9
)

(local
	local0
	babaSpeaks
)

(procedure (BabaYagaDeath)
	(User canInput: FALSE)
	(switch babaState
		(babaNAME (rm21 setScript: nameDeath))
		(babaBRAVE (rm21 setScript: braveDeath))
		(babaFETCH
			(rm21 setScript: noFetchDeath)
		)
		(babaBRING
			(rm21 setScript: noBringDeath)
		)
	)
)

(instance myIcon of DCIcon
	(properties
		view vBabaYaga1
		loop 9
		cycleSpeed 8
	)
)

(instance teleport of Sound
	(properties
		priority 15
	)
)

(instance bubbleMusic of Sound
	(properties
		number 51
		priority 5
		loop -1
	)
)

(instance reflection of Prop
	(properties
		view vEgoHoldingMirror
		loop 1
		cycleSpeed 1
	)
)

(instance babaHead of Prop
	(properties
		view vBabaYaga1
		loop 4
		cycleSpeed 1
	)
)

(instance spider of Prop
	(properties
		y 89
		x 245
		view vBabaYaga2
		cycleSpeed 1
	)
)

(instance bat of Prop
	(properties
		y 51
		x 205
		view vBabaYaga2
		loop 1
		cycleSpeed 1
	)
)

(instance TP of Prop
	(properties
		view vTeleportGreen
		cycleSpeed 1
	)
)

(instance cauldron of Prop
	(properties
		y 130
		x 86
		view vBabaYaga2
		loop 2
	)
)

(instance fire of Prop
	(properties
		y 133
		x 86
		view vBabaYaga2
		loop 3
	)
)

(instance blanket of View
	(properties
		y 151
		x 212
		view vBabaYaga2
		loop 4
	)
)

(instance baba of Actor
	(properties
		yStep 1
		view vBabaYaga1
		cycleSpeed 2
		xStep 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/baba,ogress')
				)
				(HighPrint 21 0)
				;That's one mean Ogress!
			)
			((Said 'ask,chat/baba,ogress')
				(HighPrint 21 1)
				;There's no time for idle chatter.
			)
		)
	)
)

(instance rm21 of Room
	(properties
		picture 21
		style $0001
	)
	
	(method (init)
		(LoadMany VIEW vBabaYaga1 vBabaYaga2 vFrogTransform vTeleportGreen vEgoHoldingMirror)
		(LoadMany SOUND 51 (SoundFX 28))
		(LoadMany SCRIPT DCICON 293 294)
		(super init:)
		(SolvePuzzle POINTS_ENTERBABAYAGAHUT 2)
		(if (ego has: iMandrake) (Bclr fBabaCurse))
		(cSound fade:)
		(teleport number: (SoundFX 28) init:)
		(keyDownHandler add: self)
		(mouseDownHandler addToFront: self)
		(directionHandler add: self)
		(StatusLine enable:)
		(Bclr BABAYAGA_HUT_OPEN)
		(bubbleMusic init: play:)
		(NormalEgo)
		(= yesNoTimer 0)
		(ego loop: 1 posn: 202 165 init:)
		(spider setCycle: Forward init: stopUpd:)
		(bat init:)
		(cauldron setPri: 10 setCycle: Forward init:)
		(fire setCycle: Forward init:)
		(blanket setPri: 2 ignoreActors: init: stopUpd: addToPic:)
	)
	
	(method (doit)
		(if (> yesNoTimer 1)
			(-- yesNoTimer)
		)
		(if (== yesNoTimer 1)
			(= yesNoTimer 0)
			(BabaYagaDeath)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset VISITED_BABAYAGA_INTERIOR)
		(myIcon dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(or (Said 'look/bat') (MouseClaimed bat event shiftDown))
			(HighPrint 21 2)
			;There is a bat, all folded up within his wings, hanging on the wall.
			)
			(
				(or
					(Said 'look/spider')
					(MouseClaimed spider event shiftDown)
				)
				(HighPrint 21 3)
				;Is that a SMILE on that spider's face?
			)
			(
			(or (Said 'look/web') (MouseClaimed web event shiftDown))
			(HighPrint 21 4)
			;You HATE houses with cobwebs in the corners, especially when there's an immense spider squatting cheerily in the middle of the cobweb.
			)
			(
				(or
					(Said 'look/window')
					(MouseClaimed hutWindow event shiftDown)
				)
				(HighPrint 21 5)
				;The glass seems to distort the view of the outside world.
			)
			(
				(or
					(Said 'look/caldron,pan')
					(MouseClaimed cauldron event shiftDown)
				)
				(HighPrint 21 6)
				;There's a lot of SOMETHING cooking away in the huge black cauldron.
				(HighPrint 21 7)
				;The smell is awful, the color is sickening, and you don't even want to know what's being prepared for dinner.
			)
			(
				(or
					(Said 'look/fire')
					(MouseClaimed fire event shiftDown)
				)
				(HighPrint 21 8)
				;The fire burns hot under the kettle.
			)
			(
				(or
					(Said 'look/chimney')
					(MouseClaimed chimney event shiftDown)
				)
				(HighPrint 21 9)
				;The stone hearth and chimney radiates too much heat.
			)
			(
				(or
					(Said 'look/cabinet')
					(MouseClaimed cupboard event shiftDown)
				)
				(HighPrint 21 10)
				;It's a plain wooden kitchen cupboard.
			)
			(
				(or
					(Said 'look/dagger,implement')
					(MouseClaimed knives event shiftDown)
				)
				(HighPrint 21 11)
				;The kitchen knives look old but sharp.
			)
			(
				(or
					(Said 'look/hay,bed,blanket')
					(MouseClaimed blanket event shiftDown)
				)
				(HighPrint 21 12)
				;The pile of straw covered by a blanket must be the occupant's bed.
			)
			((== (event type?) mouseDown)
				(if (not (& (event modifiers?) shiftDown))
					(if (not local0)
						(= local0 1)
						(cond 
							((ego has: iMagicMirror) (= babaState babaFINALE) (self setScript: lastWitch))
							((not (Btst VISITED_BABAYAGA_INTERIOR)) (self setScript: firstWitch))
							((== babaState babaFETCH) (= babaState babaBRING) (self setScript: (ScriptID 294 0)))
							((== babaState babaBRING) (= babaState babaFINALE) (self setScript: lastWitch))
						)
					else
						(event claimed: TRUE)
					)
				)
			)
			((== (event type?) direction)
				(if (not local0)
					(= local0 1)
					(cond 
						((ego has: iMagicMirror) (= babaState babaFINALE) (self setScript: lastWitch))
						((not (Btst VISITED_BABAYAGA_INTERIOR)) (self setScript: firstWitch))
						((== babaState babaFETCH) (= babaState babaBRING) (self setScript: (ScriptID 294 0)))
						((== babaState babaBRING) (= babaState babaFINALE) (self setScript: lastWitch))
					)
				else
					(event claimed: TRUE)
				)
			)
			((== (event type?) saidEvent)
				(cond 
					(
						(or
							(Said '[get,use,display,hold,prepare]/mirror[<magic]')
							(Said 'reflect[/spell]')
						)
						(if (ego has: iMagicMirror)
							(HighPrint 21 13)
							;You furtively grasp the Magic Mirror.
							(Bset fBabaFrog)
						else
							(PrintDontHaveIt)
						)
					)
					((Said 'get')
						(HighPrint 21 14)
						;You don't seem to be able to move.
						(if (not local0)
							(= local0 1)
							(cond 
								((ego has: iMagicMirror) (= babaState babaFINALE) (self setScript: lastWitch))
								((not (Btst VISITED_BABAYAGA_INTERIOR)) (self setScript: firstWitch))
								((== babaState babaFETCH) (= babaState babaBRING) (self setScript: (ScriptID 294 0)))
								((== babaState babaBRING) (= babaState babaFINALE) (self setScript: lastWitch))
							)
						else
							(event claimed: 1)
						)
					)
					((Said 'chat/baba')
						(HighPrint 21 15)
						;You cannot see the witch anywhere.
						)
					((Said 'chat')
						(HighPrint 21 16)
						;You might as well be talking to yourself.
						)
					((Said 'cast')
						(HighPrint 21 17)
						;Your magic is useless here.
						)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/hut,house,room]')
								(HighPrint 21 18)
								;A quick look around shows that this is a creepy place, despite the pot (cauldron?) simmering briskly on the fire.
								;Look at the size of that spider!
							)
							((Said '/baba,ogress')
								(HighPrint 21 15)
								;You cannot see the witch anywhere.
							)
							((or (Said '<up') (Said '/ceiling,roof'))
								(HighPrint 21 19)
								;The small hut has a beamed ceiling.
							)
							((or (Said '<down') (Said '/floor'))
								(HighPrint 21 20)
								;The floor is tidy, with a pile of straw in one corner.
							)
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance babaTalk of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(> yesNoTimer 0)
					(== babaState babaNAME)
					(LookFor event @userName)
				)
				(= yesNoTimer 0)
				(baba setScript: 0)
				(User canInput: FALSE)
				((ScriptID 293 0) cue:)
			)
			(
				(and
					(== (event type?) saidEvent)
					(> yesNoTimer 1)
				)
				(cond 
					((Said 'affirmative,please')
						(baba setScript: 0)
						(= yesNoTimer 0)
						(cond 
							((<= babaState babaFETCH) (User canInput: FALSE) ((ScriptID 293 0) cue:))
							((== babaState babaBRING) (User canInput: FALSE) ((ScriptID 294 0) cue:))
						)
					)
					((Said 'n') (BabaYagaDeath))
					(else (event claimed: TRUE) (BabaYagaDeath))
				)
			)
		)
	)
)

(instance firstWitch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 143 152 self)
			)
			(1
				(baba posn: 188 125 setLoop: 6 init:)
				(babaHead
					setLoop: 5
					setPri: 15
					posn: 188 91
					init:
					setCycle: Forward
				)
				(teleport play:)
				(= cycles 5)
			)
			(2
				(ego loop: 0)
				(bat setCycle: EndLoop)
				(spider startUpd:)
				(= babaSpeaks TRUE)
				(TimePrint 6 21 21)
				;"Look pets...we have a visitor!"
				(= seconds 5)
			)
			(3
				(bat setLoop: 5 setCycle: EndLoop)
				(= babaSpeaks FALSE)
				(babaHead setCycle: 0 hide:)
				(= cycles 10)
			)
			(4
				(babaHead setLoop: 4 setCycle: Forward show:)
				(spider setCycle: 0)
				(= babaSpeaks TRUE)
				(Print 21 22 #at -1 8 #width 125 #mode teJustCenter #dispose #time 8)
				(= seconds 6)
				; "Powers of Night,
				; Shadows of Day
				; Heed now my Words!
				; Henceforth you STAY!"
			)
			(5
				(= babaSpeaks FALSE)
				(baba setLoop: 6 setCycle: EndLoop)
				(= seconds 2)
			)
			(6
				(TimePrint 7 21 23)
				;Your body is frozen by the power of the witch's spell.
				(babaHead setCycle: 0 hide:)
				(= cycles 15)
			)
			(7
				(baba
					setLoop: 8
					setCycle: Forward
					setMotion: Chase ego 20 self
				)
			)
			(8
				(baba setCycle: 0)
				(babaHead
					setLoop: 5
					setCycle: Forward
					posn: (baba x?) (- (baba y?) 34)
					setPri: (+ (baba priority?) 1)
					show:
				)
				(= cycles 2)
			)
			(9
				(= babaSpeaks TRUE)
				(TimePrint 6 21 24)
				;"Well Dearies!  What shall we have for supper today?"
				(= seconds 4)
			)
			(10
				(babaHead setCycle: 0)
				(spider setCycle: Forward)
				(bat cycleSpeed: 2 setCycle: Forward)
				(= seconds 4)
			)
			(11
				(babaHead setLoop: 4 setCycle: Forward)
				(bat setCycle: 0)
				(spider setCycle: 0)
				(TimePrint 7 21 25)
				;"Hero sandwiches?
				;I had something more formal in mind."
				(= seconds 7)
			)
			(12
				(TimePrint 6 21 26)
				;"Ah...that's it!
				;Frog Legs Fricassee."
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= seconds 6)
			)
			(13
				(babaHead setLoop: 5)
				(TimePrint 6 21 27)
				;"Now how does that spell go.....?"
				(bat setCycle: 0)
				(spider setCycle: 0)
				(= seconds 6)
			)
			(14
				(babaHead setLoop: 4)
				(Print 21 28
					#at -1 12
					#width 125
					#mode teJustCenter
					#dispose
					#time 8
				)
				(= seconds 6)
				; "Hear me, oh Powers
; Of Klatha and Mana!
; Turn now my guest
; Into species called Rana!"
			)
			(15
				(= babaSpeaks FALSE)
				(babaHead stopUpd: hide:)
				(baba setLoop: 6 setCycle: EndLoop)
				(= cycles 12)
			)
			(16
				(ego view: vEgoFrogTransform loop: 3 cel: 0 setCycle: EndLoop)
				(teleport play:)
				(= cycles 10)
			)
			(17
				(baba setLoop: 8 setMotion: MoveTo 160 148 self)
			)
			(18
				(baba view: vBabaYaga1 setLoop: 7 setCycle: CycleTo 3 1 self)
			)
			(19
				(ego ignoreActors: hide:)
				(baba setCycle: EndLoop self)
			)
			(20
				(babaHead
					setLoop: 5
					posn: (- (baba x?) 1) (- (baba y?) 32)
					setPri: (+ (baba priority?) 1)
					startUpd:
					show:
				)
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= babaSpeaks TRUE)
				(TimePrint 8 21 29)
				;"I learned that spell from Erasmus, kids. Doesn't it look delicious?"
				(= seconds 8)
			)
			(21
				(= babaSpeaks FALSE)
				(spider setCycle: 0)
				(bat setCycle: 0)
				(babaHead stopUpd: hide:)
				(rm21 setScript: (ScriptID 293 0))
			)
		)
	)
)

(instance nameDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setScript: 0)
				(= yesNoTimer 0)
				(babaHead setCycle: Forward)
				(TimePrint 3 21 30)
				;"Stubborn, aren't you?"
				(= seconds 3)
			)
			(1
				(TimePrint 5 21 31)
				;"Well, you won't be so tough after you've simmered in a little wine sauce."
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(= seconds 5)
			)
			(2
				(baba setCel: 1)
				(babaHead setLoop: 5)
				(TimePrint 4 21 32)
				;"Bon appetit, Dearies!"
				(= seconds 4)
			)
			(3
				(babaHead hide:)
				(EgoDead 21 33
					#title { Now you're really in the soup!_}
					#icon myIcon 0 0)
				;Next time you are asked a question by someone who has just turned you into a frog, perhaps you should be more polite.
				;It's better than being fricasseed!
			)
		)
	)
)

(instance braveDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setScript: 0)
				(= yesNoTimer 0)
				(babaHead setCycle: Forward)
				(TimePrint 4 21 34)
				;"It's just as well.  I'd lose my dinner if you were brave."
				(= seconds 4)
			)
			(1
				(baba setCel: 1)
				(babaHead setLoop: 4)
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(TimePrint 5 21 35)
				;"Lovies, it's about time we dine."
				(= seconds 4)
			)
			(2
				(babaHead hide:)
				(EgoDead 21 36
					#title { Shame on you!_}
					#icon myIcon 0 0)
			)
			;Didn't anyone ever tell you that a hero is supposed to be brave even when he isn't?
			;Looks like you found the coward's way out...sauteed in wine sauce.
		)
	)
)

(instance noFetchDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setScript: 0)
				(= yesNoTimer 0)
				(babaHead setCycle: Forward)
				(TimePrint 4 21 37)
				;"Well, if you won't be a sweet, then you'll be my meat!"
				(= seconds 4)
			)
			(1
				(baba setCel: 1)
				(babaHead setLoop: 4)
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(TimePrint 4 21 38)
				;"Nothin' says lovin' like frog legs in the oven."
				(= seconds 4)
			)
			(2
				(babaHead hide:)
				(EgoDead 21 39
					#title { Of all the lazy....!_}
					#icon myIcon 0 0)
					;It seems a shame to have such a promising career go up in flames (assuming she decides to flambe you).
					;Wouldn't it have been better just to do a small favor for such a sweet little old Ogress?
			)
		)
	)
)

(instance noBringDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baba setScript: 0)
				(= yesNoTimer 0)
				(babaHead setCycle: Forward)
				(TimePrint 6 21 40)
				;"No mandrake for me, eh?  What a waste of spells you turned out to be."
				(= seconds 6)
			)
			(1
				(spider setCycle: Forward)
				(bat setCycle: Forward)
				(TimePrint 8 21 41)
				;"Actually, since it's my breakfast time, we'll have some amphibian omelette with bacon and legs."
				(= seconds 6)
			)
			(2
				(babaHead hide:)
				(EgoDead 21 42
					#title {Breakfast of Champions?}
					#icon myIcon 0 0)
					;At least you could have said "Yes" before you croaked!
			)
		)
	)
)

(instance lastWitch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 120 152 self)
			)
			(1
				(baba setLoop: 6 posn: 168 122 init:)
				(babaHead
					posn: (baba x?) (- (baba y?) 34)
					init:
					setPri: 15
					setCycle: Forward
				)
				(= cycles 5)
			)
			(2
				(if (Btst VISITED_BABAYAGA_INTERIOR)
					(TimePrint 4 21 43)
					;"You again?"
				else
					(TimePrint 4 21 44)
					;"Hello, Hero."
				)
				(bat setCycle: EndLoop)
				(spider startUpd:)
				(= babaSpeaks TRUE)
				(= seconds 2)
			)
			(3
				(= seconds 0)
				(ego loop: 0)
				(= babaSpeaks FALSE)
				(babaHead setCycle: 0)
				(User canInput: 1)
				(= seconds 8)
			)
			(4
				(bat setCycle: 0)
				(spider setCycle: 0)
				(User canInput: 0)
				(= babaSpeaks TRUE)
				(Print
					21 45
					#at -1 12
					#width 135
					#mode teJustCenter
					#dispose
					#time 10
				)
				(babaHead setCycle: Forward)
				(= seconds 7)
				; "Powers that rule
; Over regions soggy:
; Change this creature
; Back into a froggy!"
			)
			(5
				(if (Btst fBabaFrog)
					(client setScript: endGame)
				else
					(= babaSpeaks FALSE)
					(babaHead setCycle: 0 hide:)
					(baba setCycle: EndLoop)
					(= seconds 2)
				)
			)
			(6
				(ego view: vEgoFrogTransform loop: 3 cel: 0 setCycle: EndLoop)
				(babaHead setCycle: 0 hide:)
				(= cycles 15)
			)
			(7
				(spider setCycle: 0)
				(baba
					setLoop: 8
					setCycle: Forward
					setMotion: Chase ego 20 self
				)
			)
			(8
				(= babaSpeaks TRUE)
				(babaHead
					posn: (baba x?) (- (baba y?) 34)
					setPri: 15
					setCycle: Forward
					show:
				)
				(TimePrint 6 21 46)
				;"Ooooh...yummy!  I'm glad you returned."
				(= seconds 4)
			)
			(9
				(baba setLoop: 6 setCycle: 0)
				(EgoDead 21 47
					#title {Baba Yaga says you have good taste.}
					#icon myIcon 0 0)
					;Before you confront someone who is obviously more powerful than you are, give yourself a chance to reflect.
			)
		)
	)
)

(instance endGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= babaState babaFINALE)
				(babaHead hide:)
				(baba
					view: vBabaYaga1
					setLoop: 6
					cycleSpeed: 1
					setCycle: CycleTo 3 1 self
				)
				(ego
					view: vEgoHoldingMirror
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
			)
			(1
				(baba setCycle: EndLoop)
				(reflection
					setLoop: 1
					cel: 0
					posn: (+ (ego x?) 13) (- (ego y?) 33)
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(baba view: vFrogTransform setLoop: 0 cel: 0 setCycle: EndLoop self)
				(ego setCycle: BegLoop)
			)
			(3
				(baba setLoop: 1 cycleSpeed: 0 setCycle: Forward)
				(SolvePuzzle POINTS_TURNBABAYAGAINTOFROG 50)
				(TimePrint 3 21 48)
				;The witch is hopping mad.
				(= seconds 3)
			)
			(4
				(TimePrint 8 21 49)
				;"What have you DONE to me?
				;How DARE you use my own spell against me?
				;I'll, I'll....Oh oh!"
				(= seconds 8)
			)
			(5
				(NormalEgo)
				(Face ego baba)
				(TimePrint 10 21 50)
				;"Hear me, Oh Element of Air and Wind.
				;Give me the power to save my own skin."
				(= seconds 8)
			)
			(6
				((ScriptID 21 8) play:)
				((ScriptID 21 5)
					posn: (ego x?) (ego y?)
					setPri: 15
					cel: 0
					cycleSpeed: 4
					init:
					setCycle: CycleTo 6 1 self
				)
			)
			(7
				(ego dispose:)
				((ScriptID 21 5) setCycle: EndLoop self)
			)
			(8
				((ScriptID 21 5) dispose:)
				(Bset BABAYAGA_HUT_OPEN)
				(curRoom newRoom: 22)
			)
		)
	)
)

(instance web of RFeature
	(properties
		nsTop 70
		nsLeft 228
		nsBottom 117
		nsRight 255
	)
)

(instance hutWindow of RFeature
	(properties
		nsTop 67
		nsLeft 205
		nsBottom 100
		nsRight 227
	)
)

(instance chimney of RFeature
	(properties
		nsTop 57
		nsLeft 56
		nsBottom 142
		nsRight 113
	)
)

(instance cupboard of RFeature
	(properties
		nsTop 61
		nsLeft 111
		nsBottom 127
		nsRight 147
	)
)

(instance knives of RFeature
	(properties
		nsTop 65
		nsLeft 145
		nsBottom 86
		nsRight 165
	)
)
