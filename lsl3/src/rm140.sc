;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	local0
	theY
	filthNum
	local3
	local4
	printRet
	local6
	textRes
	local8
	[local9 99]
	[buffer 300]
)
(procedure (DoDisplay param1 theColor &tmp [str 200])
	(= theY (+ 42 (* param1 30)))
	(Format @buffer 140 8 (+ param1 96))
	(Display @buffer
		p_at 150 theY
		p_color theColor
		p_font 4
		p_width 10
	)
	(GetFarText textRes (+ (* local4 5) param1) @str)
	(Display @str
		p_at 165 theY
		p_color theColor
		p_font 4
		p_width 135
	)
)

(procedure (ButtonPressed event t l b r &tmp x y)
	(= x (event x?))
	(= y (event y?))
	(return
		(if
			(and
				(> x t)
				(> y l)
				(< x b)
				(< y r)
			)
			1
		else
			0
		)
	)
)

(procedure (localproc_07ff param1)
	(= [local9 (/ param1 16)]
		(| [local9 (/ param1 16)] (>> $8000 (mod param1 16)))
	)
)

(procedure (localproc_0819 param1)
	(return
		(if (& [local9 (/ param1 16)] (>> $8000 (mod param1 16)))
			1
		else
			0
		)
	)
)

(procedure (localproc_0835 &tmp temp0 temp1 [fileBuf 30])
	(if (!= (= temp0 (FOpen {LARRY3.DRV} 1)) -1)
		(= temp1 (FGets @fileBuf 8 temp0))
		(= [local9 0] (ReadNumber temp1))
		(= [local9 1] (ReadNumber temp1))
		(= [local9 2] (ReadNumber temp1))
	)
	(FClose temp0)
)

(procedure (localproc_0895 &tmp [fileBuf 40] temp40)
	(if (!= (= temp40 (FOpen {LARRY3.DRV} 2)) -1)
		(Format
			@fileBuf
			140
			13
			[local9 0]
			[local9 1]
			[local9 2]
			140
			14
		)
		(FPuts temp40 @fileBuf)
	)
	(FClose temp40)
)

(procedure (UpdateRect)
	(Graph GFillRect 32 150 189 302 1 15)
	(Graph GShowBits 32 150 189 302 1)
)

(instance rm140 of Room
	(properties
		picture 99
	)
	
	(method (init)
		(Load PICTURE curRoomNum)
		(Load SOUND 140)
		(Load SOUND 141)
		(Load FONT 4)
		(localproc_0835)
		(while
			(and
				(< (++ local0) 1000)
				(localproc_0819 (- (= textRes (Random 141 161)) 141))
			)
		)
		(if (>= local0 1000)
			(= [local9 0] 0)
			(= [local9 1] 0)
			(= [local9 2] 0)
			(= textRes (Random 141 161))
		)
		(localproc_07ff (- textRes 141))
		(localproc_0895)
		(Load TEXT textRes)
		(super init:)
		(= programControl FALSE)
		(HandsOn)
		(User canInput: FALSE)
		(ego init: hide:)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(method (changeState newState &tmp temp0 temp1 temp2 [str 200])
		(switch (= state newState)
			(0
				(Print
					140 1
					#title {Hello!___My name is Larry; Larry Laffer!}
					#at 10 -1
					#width 290
				)
				(Display
					140 2
					p_at 100 165
					p_color 4
					p_font 4		
					p_width 135
				)
				(if
					(== 0
						(Print
							140 3
							#title {Blush!}
							#button {Bail\nOut} 0
							#button {Oh, Go Ahead!\nTry to Offend Me!} 1
						)
					)
					(= quit 1)
					(return)
				)
				(= temp0
					(Print
						140 4
						#title {Reality Check}
						#button {Under 12} -1
						#button {13 to 17} 0
						#button {18 to 25} 18
						#button {over 25} 25
					)
				)
				(music fade:)
				(switch temp0
					(-1
						(Print 140 5)
						(= quit 1)
					)
					(0
						(Print 140 6)
						(self changeState: 5)
					)
					(else 
						(curRoom drawPic: curRoomNum 7)
						(aSuit init:)
						(addToPics add: atpFace doit:)
						(Format @buffer 140 7 temp0)
						(Print @buffer #at -1 144)
						(self cue:)
					)
				)
			)
			(1
				(= theY 42)
				(GetFarText textRes (* local4 5) @buffer)
				(= local6 (- (StrAt @buffer 0) 48))
				(= temp1 0)
				(while (<= temp1 (StrLen @buffer))
					(StrAt @str temp1 (StrAt @buffer (+ temp1 1)))
					(++ temp1)
				)
				(Display
					@str
					p_at 150 theY
					p_color 1
					p_font 4
					p_width 150
				)
				(= theY 72)
				(= temp1 1)
				(while (< temp1 5)
					(Format @buffer 140 8 (+ temp1 96))
					(Display
						@buffer
						p_at 150 theY
						p_color 1
						p_font 4
						p_width 10
					)
					(GetFarText textRes (+ (* local4 5) temp1) @str)
					(Display
						@str
						p_at 165 theY
						p_color 1
						p_font 4
						p_width 135
					)
					(++ temp1)
					(= theY (+ theY 30))
				)
			)
			(2
				(if (== printRet local6)
					(DoDisplay printRet 2)
					(music number: 140 loop: 1 play:)
					(Print 140 9 #at 190 8 #time 3 #dispose)
					(++ filthNum)
					(++ local3)
				else
					(DoDisplay printRet 4)
					(music number: 141 loop: 1 play:)
					(Print 140 10 #at 190 8 #time 3 #dispose)
					(if local3 (-- local3))
				)
				(aSuit setCel: local3 forceUpd:)
				(= seconds 3)
			)
			(3
				(cls)
				(UpdateRect)
				(= printRet 0)
				(if (< (++ local4) 5) (= state 0))
				(= cycles 11)
			)
			(4
				(Format
					@buffer 140 11
					filthNum
					(switch filthNum
						(5 {Totally Raunchiest})
						(4 {Really Filthy})
						(3 {Pretty Dirty})
						(2 {Rather Risque})
						(else  {Mother Goose})
					)
				)
				(UpdateRect)
				(Display
					@buffer
					p_at 160 60
					p_color 1
					p_font userFont
					p_width 130
				)
				(= seconds 9)
			)
			(5
				(if (== filthNum 0) (++ filthNum))
				(= filthLevel (- filthNum 1))
				(if (!= (= temp2 (FOpen {RESOURCE.LL3} 2)) -1)
					(Format @str 140 12 filthLevel)
					(FPuts temp2 @str)
				)
				(FClose temp2)
				(curRoom newRoom: 290)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 33])
		(if (and (not (event claimed?)) (== state 4))
			(self cue:)
		)
		(if
			(or
				(event claimed?)
				(!= state 1)
				(super handleEvent: event)
			)
			(return)
		)
		(switch (event type?)
			(mouseDown
				(cond 
					((ButtonPressed event 141 71 300 91)
						(= printRet 1)
						(self cue:)
					)
					((ButtonPressed event 141 101 300 121)
						(= printRet 2)
						(self cue:)
					)
					((ButtonPressed event 141 132 300 152)
						(= printRet 3)
						(self cue:)
					)
					((ButtonPressed event 141 161 300 186)
						(= printRet 4)
						(self cue:)
					)
				)
			)
			(keyDown
				(= temp0 (event modifiers?))
				(switch (event message?)
					(`#2
						(event claimed?)
						(ToggleSound)
					)
					(`a
						(= printRet 1)
						(self cue:)
					)
					(`b
						(= printRet 2)
						(self cue:)
					)
					(`c
						(= printRet 3)
						(self cue:)
					)
					(`d
						(= printRet 4)
						(self cue:)
					)
					(`A
						(= printRet 1)
						(self cue:)
					)
					(`B
						(= printRet 2)
						(self cue:)
					)
					(`C
						(= printRet 3)
						(self cue:)
					)
					(`D
						(= printRet 4)
						(self cue:)
					)
					(`@x
						(if (== (& temp0 $0004) 0)
							(event claimed: TRUE)
							(Print 140 0)
							(= filthNum 6)
							(while (u> filthNum 5)
								(= filthNum (GetNumber {from 1-5 ONLY!}))
							)
							(self changeState: 4)
						)
					)
				)
			)
		)
	)
)

(instance atpFace of PicView
	(properties
		y 52
		x 50
		view 140
		priority 15
	)
)

(instance aSuit of Prop
	(properties
		y 77
		x 83
		view 140
		loop 1
	)
)