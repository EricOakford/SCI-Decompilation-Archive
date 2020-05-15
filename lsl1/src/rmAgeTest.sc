;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmAgeTest) ;720
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PrintD)
(use LoadMany)
(use File)
(use User)
(use System)

(public
	rm720 0
)

(local
	theY
	ageQuestionsCorrect
	local2
	selectedAnswer
	local4
	local5
	local6
	local7
	[local8 99]
	[strBuf 300]
	local407
	instructDlg
	[local409 4]
	[local413 4]
)
(procedure (localproc_0a50 param1 param2 &tmp [temp0 200])
	(= theY (+ 32 (* param1 30)))
	(Format @strBuf 720 12 (+ param1 96))
	(Display @strBuf
		p_at 50 theY
		p_color param2
		p_font userFont
	)
	(GetFarText local6 (+ (* local2 5) param1) @temp0)
	(Display @temp0
		p_at 65 theY
		p_color param2
		p_font userFont
		p_width 205
	)
)

(procedure (localproc_0ab9 param1 param2 &tmp [temp0 200])
	(= theY (+ 32 (* param1 30)))
	(Format @strBuf 720 12 (+ param1 96))
	(Display
		@strBuf
		p_at 50 theY
		p_color param2
		p_font userFont
	)
	(GetFarText 719 (+ (* local2 5) param1) @temp0)
	(Display @temp0
		p_at 65 theY
		p_color param2
		p_font userFont
		p_width 205
	)
)

(procedure (localproc_0b23 param1 param2 param3 param4 param5 &tmp temp0 temp1)
	(= temp0 (param1 x?))
	(= temp1 (param1 y?))
	(return
		(if
			(and
				(> temp0 param2)
				(> temp1 param3)
				(< temp0 param4)
				(< temp1 param5)
			)
			1
		else
			0
		)
	)
)

(procedure (localproc_0b5f param1)
	(= [local8 (/ param1 16)]
		(| [local8 (/ param1 16)] (>> $8000 (mod param1 16)))
	)
)

(procedure (localproc_0b79 param1)
	(return
		(if
		(& [local8 (/ param1 16)] (>> $8000 (mod param1 16)))
			1
		else
			0
		)
	)
)

(procedure (localproc_0b95 &tmp temp0 [temp1 30])
	(if (!= (LARRY_DRV open: 1) 0)
		(LARRY_DRV read: @temp1 8)
		(= [local8 0] (ReadNumber @temp1))
		(LARRY_DRV read: @temp1 8)
		(= [local8 1] (ReadNumber @temp1))
		(LARRY_DRV read: @temp1 8 close:)
		(= [local8 2] (ReadNumber @temp1))
	)
)

(procedure (localproc_0c01 &tmp [temp0 40])
	(if (!= (LARRY_DRV open: 2) 0)
		(Format @temp0 720 20 [local8 0] [local8 1] [local8 2])
		(LARRY_DRV writeString: @temp0 close:)
	)
)

(instance rm720 of LLRoom
	(properties
		picture 721
	)
	
	(method (init &tmp temp0)
		(LoadMany SOUND 710 711 712)
		(localproc_0b95)
		(globalSound number: 710 loop: 1 vol: 127 flags: 1)
		(soundFx loop: 1 vol: 127 flags: 1)
		(while
			(and
				(< (++ temp0) 1000)
				(localproc_0b79 (- (= local6 (Random 721 752)) 721))
			)
		)
		(if (>= temp0 1000)
			(= [local8 0] 0)
			(= [local8 1] 0)
			(= [local8 2] 0)
			(= local6 (Random 721 752))
		)
		(localproc_0b5f (- local6 721))
		(localproc_0c01)
		(Load TEXT local6)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(HandsOn)
		(User canControl: 0)
		(ego init: hide:)
		(self setScript: RoomScript)
		(theGame setCursor: 999 1 310 185)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 i [temp2 200])
		(switch (= state newState)
			(0
				(Print 720 2 #title {Sturgeon General's Warning})
				(= instructDlg
					(Display 720 3
						p_at 1 156
						p_color myLowlightColor
						p_mode teJustCenter
						p_width 318
						p_font smallFont
						p_save
					)
				)
				(= temp0 0)
				(theGame setCursor: 999 1)
				(Animate (cast elements?) 0)
				(while (not temp0)
					(= temp0
						(PrintD
							{So, how old are you, anyway?}
							#new
							#button {under 15} 15
							#button {16 to 18} 16
							#button {19 to 39} 19
							#new
							#button {40 to 65} 40
							#button {66 to 99} 66
							#button {over 100} 100
							#title {No lying, please!}
						)
					)
				)
				(Display 720 4 108 instructDlg)
				(switch temp0
					(15
						(theMusic stop:)
						(soundFx number: 712 play:)
						(Print 720 5)
						(= quit TRUE)
					)
					(100
						(theMusic stop:)
						(soundFx number: 712 play:)
						(Print 720 6)
						(= quit TRUE)
					)
					(else 
						(switch temp0
							(16 (Print 720 7))
							(19 (Print 720 8))
							(40 (Print 720 9))
							(66 (Print 720 10))
						)
						(Print 720 11)
						(= cycles 11)
					)
				)
			)
			(1
				(globalSound play:)
				(GetFarText local6 (* local2 5) @strBuf)
				(= local4 (- (StrAt @strBuf 0) 48))
				(= i 0)
				(while (<= i (StrLen @strBuf))
					(StrAt @temp2 i (StrAt @strBuf (+ i 1)))
					(++ i)
				)
				(= local5
					(switch local2
						(0 myTextColor13)
						(1 myTextColor17)
						(2 myLowlightColor)
						(3 myBotBordColor)
						(else  myRgtBordColor)
					)
				)
				(= instructDlg
					(Display
						@temp2
						p_at 20 12
						p_color local5
						p_font userFont
						p_width 277
						p_save
					)
				)
				(= theY 62)
				(= i 1)
				(while (< i 5)
					(Format @strBuf 720 12 (+ i 96))
					(= [local409 (- i 1)]
						(Display
							@strBuf
							p_at 50 theY
							p_color local5
							p_font userFont
							p_save
						)
					)
					(GetFarText local6 (+ (* local2 5) i) @temp2)
					(= [local413 (- i 1)]
						(Display
							@temp2
							p_at 65 theY
							p_color local5
							p_font userFont
							p_width 205
							p_save
						)
					)
					(++ i)
					(= theY (+ theY 30))
				)
			)
			(2
				(if (or (== selectedAnswer local4) (== local4 0))
					(localproc_0a50 selectedAnswer myTextColor16)
					(soundFx number: 711 play: 127)
					(Print 720 13 #at 200 34 #time 3)
					(++ ageQuestionsCorrect)
					(++ state)
				else
					(localproc_0a50 selectedAnswer myTextColor10)
					(soundFx number: 712 play:)
				)
				(= cycles 22)
			)
			(3
				(if (not local407)
					(++ local407)
					(if (== ageQuestionsCorrect 4)
						(Print 720 14)
					else
						(Print 720 15)
					)
				else
					(Print 720 16 #at -1 19 #width 280)
					(= quit 1)
				)
				(= cycles 22)
			)
			(4
				(if modelessDialog (modelessDialog dispose:))
				(Display 720 4 108 instructDlg)
				(= i 0)
				(while (< i 4)
					(Display 720 4 108 [local409 i])
					(Display 720 4 108 [local413 i])
					(++ i)
				)
				(= selectedAnswer 0)
				(if (< (++ local2) 5) (= state 0))
				(= cycles 11)
			)
			(5
				(globalSound play:)
				(Print 720 17)
				(= local2 (Random 0 39))
				(GetFarText 719 (* local2 5) @strBuf)
				(= local4 (- (StrAt @strBuf 0) 48))
				(= i 0)
				(while (<= i (StrLen @strBuf))
					(StrAt @temp2 i (StrAt @strBuf (+ i 1)))
					(++ i)
				)
				(= local5 myTextColor17)
				(= instructDlg
					(Display
						@temp2
						p_at 20 12
						p_color local5
						p_font userFont
						p_width 277
						p_save
					)
				)
				(= theY 62)
				(= i 1)
				(while (< i 5)
					(Format @strBuf 720 12 (+ i 96))
					(= [local409 (- i 1)]
						(Display
							@strBuf
							p_at 50 theY
							p_color local5
							p_font userFont
							p_save
						)
					)
					(GetFarText 719 (+ (* local2 5) i) @temp2)
					(= [local413 (- i 1)]
						(Display
							@temp2
							p_at 65 theY
							p_color local5
							p_font userFont
							p_width 205
							p_save
						)
					)
					(++ i)
					(= theY (+ theY 30))
				)
			)
			(6
				(if (== selectedAnswer local4)
					(localproc_0ab9 selectedAnswer myTextColor16)
					(soundFx number: 711 play: 127)
					(Print 720 13 #at 200 34 #time 3)
					(++ state)
				else
					(localproc_0ab9 selectedAnswer myTextColor10)
					(soundFx number: 712 play:)
				)
				(= cycles 22)
			)
			(7
				(Print 720 18
					#title {Yo, ho. Yo, ho!}
					#button {Quit} 0
					#button {Leave} 1
					#button {OK} 4
					#button {Done} 2
					#button {Exit} 3
				)
				(= quit TRUE)
			)
			(8
				(if modelessDialog (modelessDialog dispose:))
				(Display 720 4 p_restore instructDlg)
				(= i 0)
				(while (< i 4)
					(Display 720 4 p_restore [local409 i])
					(Display 720 4 p_restore [local413 i])
					(++ i)
				)
				(Format @strBuf 720 19)
				(Display
					@strBuf
					p_at 60 60
					p_color myTextColor13
					p_font userFont
					p_width 230
				)
				(= seconds 6)
			)
			(9 (curRoom newRoom: 100))
		)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 33])
		(if (and (== state 8) (not (event claimed?)))
			(self cue:)
		)
		(if (== (event type?) keyDown)
			(= temp0 (event modifiers?))
			(switch (event message?)
				(`#2
					(cond 
						((theGame masterVolume:) (theGame masterVolume: 0))
						((> numVoices 1) (theGame masterVolume: 15))
						(else (theGame masterVolume: 1))
					)
					(event claimed: TRUE)
				)
				(`^q
					(theGame quitGame:)
					(event claimed: TRUE)
				)
				(`@x
					(if (& temp0 $0004)
						(event claimed: TRUE)
						(Print 720 0)
						(Print 720 1)
						(= local2 10)
						;(self changeState: 4)
						(self changeState: 8)
					)
				)
			)
		)
		(if
			(or
				(event claimed?)
				(and (!= state 1) (!= state 5))
				(super handleEvent: event)
			)
			(return)
		)
		(switch (event type?)
			(mouseDown
				(cond 
					((localproc_0b23 event 41 61 200 81) (= selectedAnswer 1) (self cue:))
					((localproc_0b23 event 41 91 200 111) (= selectedAnswer 2) (self cue:))
					((localproc_0b23 event 41 122 200 142) (= selectedAnswer 3) (self cue:))
					((localproc_0b23 event 41 151 200 176) (= selectedAnswer 4) (self cue:))
				)
			)
			(keyDown
				(= temp0 (event modifiers?))
				(switch (event message?)
					(KEY_a
						(= selectedAnswer 1)
						(self cue:)
					)
					(KEY_b
						(= selectedAnswer 2)
						(self cue:)
					)
					(KEY_c
						(= selectedAnswer 3)
						(self cue:)
					)
					(KEY_d
						(= selectedAnswer 4)
						(self cue:)
					)
					(KEY_A
						(= selectedAnswer 1)
						(self cue:)
					)
					(KEY_B
						(= selectedAnswer 2)
						(self cue:)
					)
					(KEY_C
						(= selectedAnswer 3)
						(self cue:)
					)
					(KEY_D
						(= selectedAnswer 4)
						(self cue:)
					)
				)
			)
		)
	)
)

(instance LARRY_DRV of File
	(properties
		name "LARRY.DRV"
	)
)
