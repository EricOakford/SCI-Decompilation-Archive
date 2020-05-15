;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmKeypad) ;505
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PrintD)
(use LoadMany)
(use Sound)
(use Actor)
(use System)

(public
	rm505 0
)

(local
	[ajaxNumber 10] = [5 5 5 8 0 3 9]
	[surveyNumber 10] = [5 5 5 6 9 6 9]
	[sierraInternationalNumber 10] = [2 0 9 6 8 3 8 9 8 9]	;outside the U.S.
	[sierraUSNumber 10] = [8 0 0 3 2 6 6 6 5 4]	;within the U.S.
	local40
	local41
	[numbersDialed 11]
	local53
	dEditFont
)
(procedure (DialedNumber param1)
	(if (not (if (== param1 1) (not local40)))
		(= [numbersDialed local40] param1)
		(if (or (== (++ local40) 7) (== local40 10))
			(cond 
				((DialedAjax) (HandsOff) (curRoom setScript: sCallAjax))
				((DialedSexline) (HandsOff) (curRoom setScript: sCallSexline))
				((== (DialedSierraInternational) 11) (HandsOff) (curRoom setScript: sCallSierra))
				((== (DialedSierraUS) 11) (HandsOff) (curRoom setScript: sCallSierra))
				((and (== (DialedSierraInternational) 8) (<= local40 7)) 0)
				((and (== (DialedSierraUS) 8) (<= local40 7)) 0)
				(else
					(ClearDialedButtons)
					(sfxWrongNumber play:)
					(Print 505 38)
					(Print 505 39)
					(sfxDialTone play: hold: 10)
				)
			)
		)
	)
)

(procedure (ClearDialedButtons &tmp i)
	(= i 0)
	(while (< i 10)
		(= [numbersDialed i] 0)
		(= local40 0)
		(++ i)
	)
)

(procedure (DialedAjax &tmp i)
	(= i 0)
	(while
	(and (<= i 7) (== [numbersDialed i] [ajaxNumber i]))
		(++ i)
	)
	(return (if (== i 8) (return 8) else (return 0)))
)

(procedure (DialedSexline &tmp i)
	(= i 0)
	(while
	(and (<= i 7) (== [numbersDialed i] [surveyNumber i]))
		(++ i)
	)
	(return (if (== i 8) (return 8) else (return 0)))
)

(procedure (DialedSierraInternational &tmp i)
	(= i 0)
	(while
	(and (<= i 10) (== [numbersDialed i] [sierraInternationalNumber i]))
		(++ i)
	)
	(return
		(cond 
			((== i 10) (return 11))
			((== i 7) (return 8))
			(else (return 0))
		)
	)
)

(procedure (DialedSierraUS &tmp i)
	(= i 0)
	(while
	(and (<= i 10) (== [numbersDialed i] [sierraUSNumber i]))
		(++ i)
	)
	(return
		(cond 
			((== i 10) (return 11))
			((== i 7) (return 8))
			(else (return 0))
		)
	)
)

(instance rm505 of LLRoom
	(properties
		lookStr {You are using the Quiki-Mart's one and only telephone.}
		picture 515
	)
	
	(method (init)
		(LoadMany PICTURE 515)
		(LoadMany VIEW 515)
		(LoadMany SOUND
			490 491 492 493 494 495 496 497 498 499 508
			500 501 502 503 505 506 509
		)
		(ego init: x: (+ (ego x?) 1000))
		(one init:)
		(two init:)
		(three init:)
		(four init:)
		(five init:)
		(six init:)
		(seven init:)
		(eight init:)
		(nine init:)
		(zero init:)
		(star init:)
		(pound init:)
		(if (Btst fPhoneRinging)
			(Bclr fPhoneRinging)
			(HandsOff)
			(curRoom setScript: sSurveyResponse)
		else
			(sfxDialTone play: hold: 10)
		)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (== (curRoom script?) 0) (++ local41))
		(if (> local41 800)
			(sfxDialTone stop:)
			(sfxWrongNumber play:)
			(Print 505 0)
			(ClearDialedButtons)
			(= local41 0)
			(sfxDialTone play: hold: 10)
		)
		(if (ego mover?) (curRoom setScript: sBackToRoom))
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk (Print 505 1))
			(verbDo (Print 505 2))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sBackToRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(ClearDialedButtons)
				(= cycles 5)
			)
			(1
				(HandsOn)
				(curRoom newRoom: rmOutside7_11)
			)
		)
	)
)

(instance sCallSexline of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 200])
		(switch (= state newState)
			(0
				(ClearDialedButtons)
				(if (Btst fDialedSurvey)
					(sfxDialTone stop:)
					(sfxPhoneBusy play:)
					(Print 505 3)
					(sfxPhoneBusy stop:)
					(sfxDialTone play: hold: 10)
					(HandsOn)
					(self dispose:)
				else
					(sfxPhoneRinging loop: 3 play: self)
					(= str1 0)
					(= str2 0)
					(= str3 0)
					(= str4 0)
					(= str5 0)
					(= str6 0)
					(= str7 0)
					(= str8 0)
					(= str9 0)
				)
			)
			(1
				(sfxPhoneAnswered play:)
				(Print 505 4)
				(SolvePuzzle fDialedSurvey 2)
				(Print 505 5)
				(= cycles 20)
			)
			(2 (Print 505 6) (= seconds 3))
			(3 (Print 505 7) (= seconds 3))
			(4 (Print 505 8) (= seconds 3))
			(5
				(= dEditFont (DEdit font?))
				(DEdit font: editFont)
				(Print 505 9)
				(while (> 2 (StrLen @str1))
					(PrintD
						#text {"So tell me, Larry: what is the best part of your body?"}
						#new
						#edit @str1 24
					)
				)
				(= seconds 3)
			)
			(6
				(Printf 505 10 @str1)
				(= seconds 3)
			)
			(7
				(while (> 2 (StrLen @str2))
					(PrintD
						#text {"And Larry, what is your favorite X-rated video?"}
						#new
						#edit @str2 51
					)
				)
				(= seconds 3)
			)
			(8
				(Print 505 11)
				(while (> 2 (StrLen @str3))
					(PrintD
						#text {"Now tell me your favorite article of clothing."}
						#new
						#edit @str3 24
					)
				)
				(= seconds 3)
			)
			(9
				(Print 505 12)
				(Print 505 13)
				(while (> 2 (StrLen @str4))
					(PrintD
						#text {"Larry, what is your favorite sex partner's first name?"}
						#new
						#edit @str4 18
					)
				)
				(= seconds 3)
			)
			(10
				(Format
					@temp0
					{"What's the best part of %s's anatomy?"}
					@str4
				)
				(while (> 2 (StrLen @str5))
					(PrintD #text @temp0 #new #edit @str5 24)
				)
				(= seconds 3)
			)
			(11
				(Format
					@temp0
					{"What is %s's sexiest article of clothing?"}
					@str4
				)
				(while (> 2 (StrLen @str6))
					(PrintD #text @temp0 #new #edit @str6 24)
				)
				(= seconds 3)
			)
			(12
				(Format @temp0 {"What's %s's favorite object?"} @str4)
				(while (> 2 (StrLen @str7))
					(PrintD #text @temp0 #new #edit @str7 24)
				)
				(= seconds 3)
			)
			(13
				(while (> 2 (StrLen @str8))
					(PrintD
						#text {"And where do you two most enjoy making love?"}
						#new
						#edit @str8 24
					)
				)
				(= seconds 3)
			)
			(14
				(Print 505 14)
				(Format
					@temp0
					{"And, finally, what do you and %s like to do when you're together?"}
					@str4
				)
				(while (> 2 (StrLen @str9))
					(PrintD #text @temp0 #new #edit @str9 50)
				)
				(DEdit font: dEditFont)
				(= seconds 3)
			)
			(15
				(Print 505 15)
				(sfxPhoneHangUp play:)
				(sfxDialTone play: hold: 10)
				(Print 505 16)
				(Print 505 17)
				(Bset fAnsweredSurvey)
				(= cycles 10)
			)
			(16 (HandsOn) (self dispose:))
		)
	)
)

(instance sSurveyResponse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sfxDialTone stop:)
				(Bclr fAnsweredSurvey)
				(= seconds 2)
			)
			(1
				(Print 505 18)
				(= seconds 3)
			)
			(2
				(Printf 505 19 @str4 @str8 @str3 67 -1 20)
				(= seconds 3)
			)
			(3
				(Printf 505 20 @str6 @str2 67 -1 20)
				(= seconds 3)
			)
			(4
				(Printf 505 21 @str1 @str7 @str5)
				(= seconds 3)
			)
			(5
				(Printf 505 22 @str9)
				(= seconds 3)
			)
			(6
				(Print 505 23)
				(sfxPhoneHangUp play:)
				(sfxDialTone play: hold: 10)
				(= seconds 3)
			)
			(7
				(Bset fHeardSurveyFirstTime)
				(HandsOn)
				(self dispose:)
				(curRoom newRoom: rmOutside7_11)
			)
		)
	)
)

(instance sCallAjax of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ClearDialedButtons)
				(if debugging (Bset fHeardAjaxAd))
				(cond 
					((and (Btst fHeardAjaxAd) (not (Btst fOrderedWineFirstTime)))
						(SolvePuzzle fOrderedWineFirstTime 5)
						(sfxPhoneRinging loop: 3 play: self)
					)
					((or (not (Btst fHeardAjaxAd)) (Btst fOrderedWineFirstTime))
						(sfxPhoneBusy play:)
						(Print 505 24)
						(sfxPhoneBusy stop:)
						(sfxDialTone play: hold: 10)
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(1
				(sfxPhoneAnswered play:)
				(Print 505 25)
				(= cycles 1)
			)
			(2
				(Print 505 26)
				(= seconds 3)
			)
			(3
				(Print 505 27)
				(= seconds 3)
			)
			(4
				(Print 505 28)
				(= seconds 3)
			)
			(5
				(Print 505 29)
				(Print 505 30)
				(sfxPhoneHangUp play:)
				(= seconds 3)
			)
			(6
				(sfxDialTone play: hold: 10)
				(Print 505 31 #at -1 140)
				(Bset fOrderedWine)
				(= cycles 1)
			)
			(7 (HandsOn) (self dispose:))
		)
	)
)

(instance sCallSierra of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle fDialedSierra 5)
				(ClearDialedButtons)
				(sfxPhoneRinging loop: 2 play: self)
			)
			(1
				(sfxPhoneAnswered play:)
				(Print 505 32)
				(= seconds 2)
			)
			(2
				(Print 505 33)
				(= seconds 2)
			)
			(3
				(Print 505 34)
				(= seconds 2)
			)
			(4
				(Print 505 35)
				(= seconds 2)
			)
			(5
				(Print 505 36)
				(sfxPhoneHangUp play:)
				(= seconds 2)
			)
			(6
				(sfxDialTone play: hold: 10)
				(Print 505 37)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sfxPhone of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance sfxDialTone of Sound
	(properties
		flags mNOPAUSE
		number 490
		loop -1
	)
)

(instance sfxPhoneAsterisk of Sound
	(properties
		flags mNOPAUSE
		number 500
	)
)

(instance sfxPhonePoundSign of Sound
	(properties
		flags mNOPAUSE
		number 501
	)
)

(instance sfxPhone0 of Sound
	(properties
		number 508
	)
)

(instance sfxPhoneBusy of Sound
	(properties
		flags mNOPAUSE
		number 502
		loop -1
	)
)

(instance sfxPhoneRinging of Sound
	(properties
		flags mNOPAUSE
		number 503
	)
	
	(method (check)
		(DoSound UpdateCues self)
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(cond 
				((> (self loop?) 1)
					(self loop: (- (self loop?) 1))
					(DoSound PlaySound self 0)
				)
				((IsObject client) (client cue: self))
			)
		)
	)
)

(instance sfxPhoneAnswered of Sound
	(properties
		flags mNOPAUSE
		number 505
	)
)

(instance sfxPhoneHangUp of Sound
	(properties
		flags mNOPAUSE
		number 506
	)
)

(instance sfxWrongNumber of Sound
	(properties
		flags mNOPAUSE
		number 509
	)
)

(instance sButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sfxDialTone stop:)
				(sfxPhone
					number: (if (== client zero) 508 else (+ 490 (client loop?)))
					play:
				)
				(client setCel: 1)
				(= local41 0)
				(= cycles 4)
			)
			(1
				(client setCel: 0)
				(= cycles 1)
			)
			(2
				(HandsOn)
				(DialedNumber (client loop?))
				(= cycles 1)
			)
			(3
				(= local53 0)
				(self dispose:)
			)
		)
	)
)

(instance one of Prop
	(properties
		x 110
		y 71
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(verbTalk (Print 505 1))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance two of Prop
	(properties
		x 135
		y 71
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance three of Prop
	(properties
		x 161
		y 71
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 3
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance four of Prop
	(properties
		x 114
		y 91
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance five of Prop
	(properties
		x 137
		y 91
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance six of Prop
	(properties
		x 162
		y 91
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 6
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance seven of Prop
	(properties
		x 116
		y 111
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 7
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance eight of Prop
	(properties
		x 140
		y 111
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 8
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance nine of Prop
	(properties
		x 164
		y 112
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 9
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance zero of Prop
	(properties
		x 140
		y 132
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self setScript: sButton))
			(else 
				(one doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance star of Prop
	(properties
		x 118
		y 132
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(= local53 -1)
				(self setScript: sButton)
				(sfxPhoneAsterisk play:)
			)
			(one
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pound of Prop
	(properties
		x 164
		y 130
		description {the button}
		sightAngle 40
		lookStr {Thoroughly inspecting the touchtone telephone, you conclude that it's just like any other telephone.}
		view 515
		loop 11
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(= local53 -2)
				(self setScript: sButton)
				(sfxPhonePoundSign play:)
			)
			(verbTalk (Print 505 1))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
