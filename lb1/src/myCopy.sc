;;; Sierra Script 1.0 - (do not remove this comment)
(script# 414)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	myCopy 0
)

(local
	fingerCel
	fingerLoop
	saveBits
	saveBits2
	local4
	local5 = [0 36 40 52 0 62 59 85 0 89 37 107 0 116 54 137 0 129 65 161 34 164 107 182 129 164 204 182 219 164 298 182 250 31 303 53 250 60 303 84 250 90 303 118 250 126 303 154]
	local53 = [0 1 5 2 3 4 7 6 8 11 9 10 3 0 7 1 6 5 11 2 4 8 10 9]
	local77 = [10 39 10 68 10 94 10 120 10 143 62 168 159 168 255 168 266 39 266 67 266 97 266 133]
	local101
	local102
	local103
	local104
)
(procedure (localproc_0136)
	(SetCursor 1 1
		[local77 (* local101 2)]
		[local77 (+ (* local101 2) 1)]
	)
)

(procedure (localproc_0150 param1 param2 &tmp i)
	(for ((= i 0)) (< i 12) ((++ i))
		(if
			(and
				(> param1 [local5 (* i 4)])
				(> param2 [local5 (+ (* i 4) 1)])
				(< param1 [local5 (+ (* i 4) 2)])
				(< param2 [local5 (+ (* i 4) 3)])
			)
			(return i)
		)
	)
	(return 13)
)

(procedure (ClearCP)
	(cast eachElementDo: #hide)
	(DrawPic 88 WIPEUP TRUE 1)
)

(procedure (WrongAnswer)
	(ClearCP)
	(Print 414 0
		#mode teJustCenter
	)
	(= quit TRUE)
)

(instance Logo of Prop)

(instance Finger of Prop)

(instance Glass of Actor)

(instance Mood of Sound)

(instance myCopy of Room
	(properties
		picture 88
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(TheMenuBar state: FALSE)
		(= local102 1)
		(cSound number: 52 loop: -1 play:)
		(= fingerCel (/ (Random 0 600) 100))
		(= fingerLoop (/ (Random 1 1000) 250))
		(SetCursor 1 1 320 20)
		(Logo
			view: 553
			loop: 4
			cel: 1
			posn: 161 120
			init:
			stopUpd:
		)
		(Finger
			view: 553
			loop: fingerLoop
			cel: fingerCel
			posn: 161 110
			init:
			hide:
		)
		(Glass
			view: 553
			setLoop: 5
			setCel: 0
			setStep:
			(switch howFast
				(1 6)
				(else  3)
			) 3
			posn: 161 140
			init:
		)
		(self setScript: identify)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp soundOn)
		(if (not local104)
			(if local102
				(switch (event type?)
					(keyDown
						(cond 
							((== (event message?) ENTER)
								(Logo dispose:)
								(Glass posn: 162 140 setMotion: 0 stopUpd:)
								(Finger show: stopUpd:)
								(identify
									state: 4
									seconds: 0
									cycles: 0
								)
								(self cue:)
							)
							((== (event message?) `#2)
								(= soundOn (DoSound SoundOn))
								(DoSound SoundOn (not soundOn))
							)
						)
					)
				)
				(event claimed: TRUE)
				(return)
			)
			(switch (event type?)
				(joyDown
					(= local104 1)
					(= local102 1)
					(if
						(==
							(= local4 (localproc_0150 (event x?) (event y?)))
							[local53 (+ (* fingerLoop 6) fingerCel)]
						)
						(self cue:)
					else
						(WrongAnswer)
					)
					(event claimed: TRUE)
				)
				(mouseDown
					(= local104 1)
					(= local102 1)
					(if
						(==
							(= local4 (localproc_0150 (event x?) (event y?)))
							[local53 (+ (* fingerLoop 6) fingerCel)]
						)
						(self cue:)
					else
						(WrongAnswer)
					)
					(event claimed: TRUE)
				)
				(direction
					(switch (event message?)
						(dirN
							(if (and (!= local101 0) (!= local101 8))
								(if (== local101 7) (= local101 11) else (-- local101))
							)
						)
						(dirS
							(if (or (< local101 5) (> local101 7))
								(if (== local101 11) (= local101 7) else (++ local101))
							)
						)
						(dirE
							(if (< local101 7)
								(cond 
									((< local101 4) (= local101 (+ local101 8)))
									((> local101 4) (++ local101))
									(else (= local101 11))
								)
							)
						)
						(dirW
							(if (> local101 5)
								(if (> local101 7)
									(= local101 (- local101 8))
								else
									(-- local101)
								)
							)
						)
					)
					(localproc_0136)
					(event claimed: TRUE)
				)
				(keyDown
					(cond 
						((== (event message?) ENTER)
							(= local102 1)
							(= local104 1)
							(if
								(==
									(= local4 (localproc_0150 (event x?) (event y?)))
									[local53 (+ (* fingerLoop 6) fingerCel)]
								)
								(self cue:)
							else
								(WrongAnswer)
							)
						)
						((== (event message?) `#2)
							(= soundOn (DoSound SoundOn))
							(DoSound SoundOn (not soundOn))
						)
					)
					(localproc_0136)
					(event claimed: TRUE)
				)
			)
		)
	)
	
	(method (newRoom n)
		(cSound stop:)
		(super newRoom: n)
	)
)

(instance identify of Script
	
	(method (changeState newState &tmp [str 25])
		(switch (= state newState)
			(0
				(= saveBits2
					(Display 414 1
						p_at 90 16
						p_width 256
						p_color vWHITE
						p_back -1
						p_font SYSFONT
						p_save
					)
				)
				(= cycles 20)
			)
			(1
				(= saveBits
					(Display
						(Format @str 414 2 version)
						p_mode teJustCenter
						p_at 35 155
						p_width 250
						p_color vWHITE
						p_back -1
						p_font SYSFONT
						p_save
					)
				)
				(= seconds 4)
			)
			(2
				(if howFast
					(Glass setMotion: MoveTo 240 140 self)
				else
					(= cycles 1)
				)
			)
			(3
				(Logo dispose:)
				(= cycles 1)
			)
			(4
				(Finger show: stopUpd:)
				(if howFast
					(Glass setMotion: MoveTo 163 140 self)
				else
					(= cycles 1)
				)
			)
			(5
				;we now skip the copy protection automatically
				(self cue:)
				
				;this code will be left here for historical reference, but will not
				; be executed
;;;				(SetCursor 1 1 10 39)
;;;				(= local102 0)
;;;				(Display 414 3
;;;					p_restore saveBits
;;;				)
;;;				(Display 414 3
;;;					p_restore saveBits2
;;;				)
;;;				(Display 414 4
;;;					p_at 32 8
;;;					p_width 256
;;;					p_color vWHITE
;;;					p_back -1
;;;					p_font 4
;;;					p_mode teJustCenter
;;;					p_save
;;;				)
;;;				(Display 414 5
;;;					p_at 5 40
;;;					p_width 101
;;;					p_color vWHITE
;;;					p_back -1
;;;					p_font 4
;;;					p_save
;;;				)
;;;				(Display 414 6
;;;					p_at 40 170
;;;					p_width 320
;;;					p_color vWHITE
;;;					p_back -1
;;;					p_font 4
;;;					p_save
;;;				)
;;;				(Display 414 7
;;;					p_at 140 170
;;;					p_width 320
;;;					p_color vWHITE
;;;					p_back -1
;;;					p_font 4
;;;					p_save
;;;				)
;;;				(Display 414 8
;;;					p_at 230 170
;;;					p_width 320
;;;					p_color vWHITE
;;;					p_back -1
;;;					p_font 4
;;;					p_save
;;;				)
;;;				(Display 414 9
;;;					p_at 255 40
;;;					p_width 101
;;;					p_color vWHITE
;;;					p_back -1
;;;					p_font 4
;;;					p_save
;;;				)
			)
			(6
				(= local104 1)
				(= local102 1)
				(ClearCP)
				(Print 414 10
					#mode teJustCenter
				)
				(SetCursor HAND_CURSOR TRUE 300 0)
				(self setScript: (ScriptID 409 0))
			)
		)
	)
)
