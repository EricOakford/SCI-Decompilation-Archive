;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include system.sh) ;for p_at, etc.
(include keys.sh)
;(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm8 0
)

(local
	;[local0 100]
	[str 41] ;pCommandString
	eventMessage
	;local142 unused?
	local143
	local144 ;currentFolder (personnel:2, homicide:4, vice:7, burglary:5, firearms:6)
	local145
	local146 ;Change Director
	local147 ;inPersonnelDIR
	local148 ;passordRequired
	local149 ;inSierraDIR
	local150 ;ready for input?
	local151 ;cue computerScript
	local152 ;selectedRecordNum
	;local153 unused?
	local154 ;inputCursorX
)
(procedure (localproc_000c &tmp newEvent) ;clean up DIR CD listing?
	(while ((= newEvent (Event new:)) type?)
		(newEvent dispose:)
	)
	(newEvent dispose:)
)

(procedure (localproc_0031 param1) ;letter to uppercase
	(return
		(if (and (<= 97 param1) (<= param1 122))
			(return (- param1 32))
		else
			(return param1)
		)
	)
)

(procedure (LocPrint) ;display record
	(Print &rest #font 7 #width 168 #at 70 10)
)

(instance compCursor of Prop
	(properties)
)

(instance fileCursor of Prop
	(properties)
)

(instance lite1 of View
	(properties)
)

(instance lite2 of View
	(properties)
)

(instance shaw of View
	(properties)
)

(instance rm8 of Room
	(properties
		picture 8
		style $0007
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(User canInput: 1)
		(Load VIEW 9)
		(lite1
			view: 9
			loop: 2
			cel: 0
			posn: 256 178
			init:
			stopUpd:
		)
		(lite2
			view: 9
			loop: 2
			cel: 1
			posn: 234 143
			init:
			stopUpd:
		)
		(shaw
			view: 9
			loop: 3
			cel: 0
			posn: 83 150
			init:
			addToPic:
		)
		(Format @str 8 0)
		(self setScript: rm8Script)
	)
)

(instance rm8Script of Script
	(properties)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					;((Said '/book,instruction,cocksucker') (Print 8 1)) 
					((Said '/book,instruction,dos') (Print 8 1)) 
					((Said 'look/switch,button,power') (Print 8 2))
					((Said 'look/computer') (Print 8 3))
					((Said 'exit,walk,go,quit') (curRoom newRoom: prevRoomNum))
					((Said 'type,enter,cd,cd') (Print 8 4))
					(
						(or
							(Said 'turn,log<on[/computer,power,button]')
							(Said 'activate,use,logon[/computer]')
							;(Said 'flip,press,activate,press[/button,power,switch]')
							(Said 'flip,press,activate,push[/button,power,switch]')
						)
						(lite1 hide:)
						(lite2 hide:)
						(rm8 setScript: computerScript)
					)
					((Said '[<around,at][/(anyword)]') (Print 8 5))
				)
			)
		)
	)
)

(instance computerScript of Script
	(properties)
	
	(method (doit)
		(if (> local151 1) (-- local151))
		(if (== local151 1) (= local151 0) (self cue:))
		(if
			(and
				(not local149)
				(not local147)
				(cast contains: fileCursor)
			)
			(fileCursor dispose:)
			(self changeState: 1)
		)
		(cond 
			((<= (compCursor x?) 123)
				(compCursor x: 123)
			)
			((>= (compCursor x?) 236)
				(compCursor x: 236)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(compCursor
					view: 9
					posn: 123 22
					cycleSpeed: 3
					setCycle: Forward
					init:
				)
				(= local151 5)
			)
			(1
				(= local143 24)
				(while (<= local143 114)
					(Display 8 6
						p_at 73 local143
						p_font 7
						p_color 0
						p_back 0
					)
					(= local143 (+ local143 10))
				)
				(if (cast contains: fileCursor)
					(fileCursor dispose:)
				)
				(if (not local146)
					(self cue:)
				)
			)
			(2
				(= local150 1)
				(Display 8 7 
					p_at 73 14 
					p_font 7 
					p_color 0
				)
				(Display 8 8
					p_at 73 14
					p_font 7
					p_color 9
					p_back 0
				)
			)
			(3
				(Display 8 9
					p_at 73 14
					p_font 7
					p_color 0
					p_back 0
				)
				(Display 8 9
					p_at 73 15
					p_font 7
					p_color 0
					p_back 0
				)
				(Display 8 10 ;session complete
					p_at 73 14
					p_font 7
					p_color 14
					p_back 0
				)
				(= newRoomNum prevRoomNum)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 4])
		(switch (event type?)
			(direction ;evJOYSTICK
				(if (or local147 local149)
					(event claimed: 1)
					(switch (event message?)
						(JOY_UP
							(cond 
								((> (fileCursor y?) 40)
									(fileCursor
										posn: (fileCursor x?) (- (fileCursor y?) 10)
									)
									(-- local152)
								)
								((== (fileCursor x?) 71)
									(= local152 11)
									(fileCursor posn: 162 33)
								)
								(else (= local152 1)
									(fileCursor posn: 71 33)
								)
							)
						)
						(JOY_DOWN
							(cond 
								((< (fileCursor y?) 114)
									(++ local152)
									(fileCursor
										posn: (fileCursor x?) (+ (fileCursor y?) 10)
									)
								)
								((== (fileCursor x?) 71)
									(= local152 20)
									(fileCursor posn: 162 123)
								)
								(else (= local152 10)
									(fileCursor posn: 71 123)
								)
							)
						)
						(else 
							(if (== (fileCursor x?) 71)
								(= local152 (+ local152 10))
								(fileCursor posn: 162 (fileCursor y?))
							else
								(= local152 (- local152 10))
								(fileCursor posn: 71 (fileCursor y?))
							)
						)
					)
				)
			)
			(keyDown ;evKEYBOARD
				(if
					(or
						(== (= eventMessage (event message?)) KEY_F6)
						(== eventMessage KEY_F8)
						(== eventMessage KEY_F10)
					)
					(Print 8 11) ;disable gun
					(event claimed: 1)
				)
				(if local150
					(event claimed: 1)
					(= local154 (StrLen @str))
					(cond 
						(
							(and
								(< KEY_SPACE (event message?))
								(< (event message?) KEY_DELETE) ;127
								(< local154 13)
							)
							(StrAt @str local154 (localproc_0031 (event message?)))
							(++ local154)
							(StrAt @str local154 0)
							(Display (Format @temp0 {%c} eventMessage)
								p_at (compCursor x?) (- (compCursor y?) 8)
								p_font 7
								p_color 9
								p_back 0
							)
							(compCursor x: (+ (compCursor x?) 6))
						)
						((and (== JOY_UPLEFT eventMessage) local154)
							(-- local154)
							(StrAt @str local154 0)
							(compCursor x: (- (compCursor x?) 6))
							(Display 8 12
								p_at (compCursor x?) (- (compCursor y?) 8)
								p_font 7
								p_color 0
								p_back 0
							)
						)
						((== eventMessage KEY_RETURN)
							(Display 8 13
								p_at 123 14
								p_color 0
								p_font 7
								p_back 0
							)
							(Display 8 13
								p_at 123 15
								p_color 0
								p_font 7
								p_back 0
							)
							(= local154 0)
							(compCursor x: 123)
							(cond 
								(local146
									(cond 
										((not (StrCmp @str {CRIMINAL}))
											(= local145 3)
											(= local146 0)
											(self changeState: 1)
										)
										((not (StrCmp @str {SIERRA}))
											(= local145 1)
											(= local146 0)
											(self changeState: 1))
										((not (StrCmp @str {PERSONNEL}))
											(= local144 2)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										((and (not (StrCmp @str {HOMICIDE})) (== local145 3))
											(= local144 4)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										((and (not (StrCmp @str {VICE})) (== local145 3))
											(= local144 7)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										((and (not (StrCmp @str {BURGLARY})) (== local145 3))
											(= local144 5)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										((and (not (StrCmp @str {FIREARMS})) (== local145 3))
											(= local144 6)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										(else (= local145 0)
											(= local146 0)
											(self changeState: 1)
										)
									)
									(if local148
										(Display 8 14
											p_at 73 14
											p_color 0
											p_font 7
											p_back 0
										)
										(Display 8 15
											p_at 73 14
											p_color 9
											p_font 7
											p_back 0
										)
									)
								)
								(local148
									(= local148 0)
									(cond 
										(
										(and (not (StrCmp @str {ICECREAM})) (== local144 4))
											(= local145 local144))
										(
										(and (not (StrCmp @str {PISTACHIO})) (== local144 2))
											(= local145 local144)
											(SolvePuzzle 2 120)
										)
										((and (not (StrCmp @str {MIAMI})) (== local144 7))
											(= local145 local144)
											(SolvePuzzle 2 121)
										)
										(else
											(Print 8 16 #time 3)
										)
									)
									(self changeState: 1)
								)
								(local149
									(switch local152
										(1 (LocPrint 8 17))
										(2 (LocPrint 8 18))
										(3 (LocPrint 8 19))
										(4 (LocPrint 8 20))
										(5
											(LocPrint 8 21)
											(LocPrint 8 22)
										)
										(6 (LocPrint 8 23))
										(7
											(LocPrint 8 24)
											(LocPrint 8 25)
										)
										(8 (LocPrint 8 26))
										(9 (LocPrint 8 27))
										(10 (LocPrint 8 28))
										(11 (LocPrint 8 29))
										(12 (LocPrint 8 30))
										(13 (LocPrint 8 31))
										(14 (LocPrint 8 32))
										(15 (LocPrint 8 33))
									)
								)
								(local147
									(switch local145
										(4
											(switch local152
												(1
													(LocPrint 8 34)
													(LocPrint 8 35)
												)
												(2 (LocPrint 8 36))
												(3
													(LocPrint 8 37)
													(LocPrint 8 38)
												)
												(4 (LocPrint 8 39))
												(5 (LocPrint 8 40))
												(6
													(LocPrint 8 41)
													(LocPrint 8 42)
												)
												(7 (LocPrint 8 43))
												(11
													(LocPrint 8 44)
													(LocPrint 8 45)
												)
												(12
													(LocPrint 8 46)
													(LocPrint 8 47)
												)
												(13 (LocPrint 8 48))
												(14 (LocPrint 8 49))
												(15 (LocPrint 8 50))
												(16 (LocPrint 8 51))
											)
										)
										(2
											(switch local152
												(1
													(LocPrint 8 52)
													(LocPrint 8 53)
												)
												(2
													(LocPrint 8 54)
													(LocPrint 8 55)
													(LocPrint 8 56)
												)
												(3
													(LocPrint 8 57)
													(LocPrint 8 58)
													(LocPrint 8 59)
												)
												(4
													(LocPrint 8 60)
													(LocPrint 8 61)
													(LocPrint 8 62)
												)
												(5
													(LocPrint 8 63)
													(LocPrint 8 64)
												)
												(6
													(LocPrint 8 65)
													(LocPrint 8 66)
												)
												(7
													(LocPrint 8 67)
													(LocPrint 8 68)
												)
												(8
													(LocPrint 8 69)
													(LocPrint 8 70)
													(LocPrint 8 71)
												)
												(9
													(LocPrint 8 72)
													(LocPrint 8 73)
												)
												(10
													(LocPrint 8 74)
													(LocPrint 8 75)
													(LocPrint 8 76)
													(Bset 56) ;read Pratt record
												)
												(11
													(LocPrint 8 77)
													(LocPrint 8 78)
												)
												(12
													(LocPrint 8 79)
													(LocPrint 8 80)
													(LocPrint 8 81)
												)
												(13
													(LocPrint 8 82)
													(LocPrint 8 83)
													(LocPrint 8 84)
													(LocPrint 8 85)
												)
												(14
													(LocPrint 8 86)
													(LocPrint 8 87)
												)
												(15
													(LocPrint 8 88)
													(LocPrint 8 89)
												)
												(16
													(LocPrint 8 90)
													(LocPrint 8 91)
												)
												(17 (LocPrint 8 92))
											)
										)
										(7
											(switch local152
												(1
													(LocPrint 8 93)
													(LocPrint 8 94)
												)
												(2
													(LocPrint 8 95)
													(LocPrint 8 96)
												)
												(3
													(LocPrint 8 97)
													(LocPrint 8 98)
												)
												(4
													(LocPrint 8 99)
													(LocPrint 8 100)
												)
												(5
													(LocPrint 8 101)
													(LocPrint 8 102)
												)
												(6
													(LocPrint 8 103)
													(LocPrint 8 104)
												)
												(7
													(LocPrint 8 105)
													(LocPrint 8 106)
												)
												(8
													(LocPrint 8 107)
													(LocPrint 8 108)
												)
												(9
													(LocPrint 8 109)
													(LocPrint 8 110)
												)
												(11
													(LocPrint 8 111)
													(LocPrint 8 112)
												)
												(12
													(LocPrint 8 113)
													(LocPrint 8 114)
												)
												(13
													(LocPrint 8 115)
													(LocPrint 8 116)
												)
												(14
													(LocPrint 8 117)
													(LocPrint 8 118)
												)
												(15
													(LocPrint 8 119)
													(LocPrint 8 120)
												)
												(16
													(LocPrint 8 121)
													(LocPrint 8 122)
												)
											)
										)
									)
								)
							)
							(Format @str 8 0)
						)
					)
					(cond 
						(
							(and
								(not (StrCmp @str {DIR}))
								(not local146)
								(not local148)
							)
							(Format @str 8 0)
							(Display 8 13
								p_at 123 14
								p_color 0
								p_font 7
								p_back 0
							)
							(Display 8 13
								p_at 123 15
								p_color 0
								p_font 7
								p_back 0
							)
							(compCursor x: 123)
							(switch local145
								(0
									(Display 8 123
										p_at 73 24
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 124
										p_at 73 34
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 125
										p_at 73 44
										p_color 9
										p_font 7
										p_back 0
									)
								)
								(1
									(Display 8 126
										p_at 73 24
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 127
										p_at 73 34
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 128
										p_at 73 44
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 129
										p_at 73 54
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 130
										p_at 73 64
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 131
										p_at 73 74
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 132
										p_at 73 84
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 133
										p_at 73 94
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 134
										p_at 73 104
										p_color 9
										p_font 7
										p_back 0
									)
									;(Display (Format @local0 8 135)
									(Display 8 135
										p_at 73 114
										p_color 9
										p_font 7
										p_back 0
									)
									;(Display (Format @local0 8 136)
									(Display 8 136
										p_at 160 24
										p_color 9
										p_font 7
										p_back 0
									)
									;(Display (Format @local0 8 137)
									(Display 8 137
										p_at 160 34
										p_color 9
										p_font 7
										p_back 0
									)
									;(Display (Format @local0 8 138)
									(Display 8 138
										p_at 160 44
										p_color 9
										p_font 7
										p_back 0
									)
									;(Display (Format @local0 8 139)
									(Display 8 139
										p_at 160 54
										p_color 9
										p_font 7
										p_back 0
									)
									;(Display (Format @local0 8 140)
									(Display 8 140
										p_at 160 64
										p_color 9
										p_font 7
										p_back 0
									)
									(= local149 1)
									(= local152 1)
									(fileCursor view: 9 loop: 1 posn: 71 32 init:)
								)
								(2
									;(Display (Format @local0 8 141)
									(Display 8 141
										p_at 73 24
										p_color 9
										p_font 7
									)
									;(Display (Format @local0 8 142)
									(Display 8 142
										p_at 73 34
										p_color 9
										p_font 7
									)
									;(Display (Format @local0 8 143)
									(Display 8 143
										p_at 73 44
										p_color 9
										p_font 7
									)
									;(Display (Format @local0 8 144)
									(Display 8 144
										p_at 73 54
										p_color 9
										p_font 7
									)
									;(Display (Format @local0 8 145)
									(Display 8 145
										p_at 73 64
										p_color 9
										p_font 7
									)
									;(Display (Format @local0 8 146)
									(Display 8 146
										p_at 73 74
										p_color 9
										p_font 7
									)
									(Display 8 147
										p_at 73 84
										p_color 9
										p_font 7
									)
									(Display 8 148
										p_at 73 94
										p_color 9
										p_font 7
									)
									(Display 8 149
										p_at 73 104
										p_color 9
										p_font 7
									)
									(Display 8 150
										p_at 73 114
										p_color 9
										p_font 7
									)
									(Display 8 151
										p_at 155 24
										p_color 9
										p_font 7
									)
									(Display 8 152
										p_at 155 34
										p_color 9
										p_font 7
									)
									(Display 8 153
										p_at 155 44
										p_color 9
										p_font 7
									)
									(Display 8 154
										p_at 155 54
										p_color 9
										p_font 7
									)
									(Display 8 155
										p_at 155 64
										p_color 9
										p_font 7
									)
									(Display 8 156
										p_at 155 74
										p_color 9
										p_font 7
									)
									(Display 8 157
										p_at 155 84
										p_color 9
										p_font 7
									)
									(= local147 1)
									(= local152 1)
									(fileCursor view: 9 loop: 1 posn: 71 32 init:)
								)
								(3
									(Display 8 158
										p_at 73 24
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 159
										p_at 73 34
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 160
										p_at 73 44
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 161
										p_at 73 54
										p_color 9
										p_font 7
										p_back 0
									)
								)
								(4
									(Display 8 162
										p_at 73 24
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 163
										p_at 73 34
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 164
										p_at 73 44
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 165
										p_at 73 54
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 166
										p_at 73 64
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 167
										p_at 73 74
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 168
										p_at 73 84
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 169
										p_at 158 24
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 170
										p_at 158 34
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 171
										p_at 158 44
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 172
										p_at 158 54
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 173
										p_at 158 64
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 174
										p_at 158 74
										p_color 9
										p_font 7
										p_back 0
									)
									(= local147 1)
									(= local152 1)
									(fileCursor view: 9 loop: 1 posn: 71 32 init:)
								)
								(7
									(Display 8 175
										p_at 73 24
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 176
										p_at 73 34
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 177
										p_at 73 44
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 178
										p_at 73 54
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 179
										p_at 73 64
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 180
										p_at 73 74
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 181
										p_at 73 84
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 182
										p_at 73 94
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 183
										p_at 73 104
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 184
										p_at 154 24
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 185
										p_at 154 34
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 186
										p_at 154 44
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 187
										p_at 154 54
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 188
										p_at 154 64
										p_color 9
										p_font 7
										p_back 0
									)
									(Display 8 189
										p_at 154 74
										p_color 9
										p_font 7
										p_back 0
									)
									(= local147 1)
									(= local152 1)
									(fileCursor view: 9 loop: 1 posn: 71 32 init:)
								)
							)
							(localproc_000c)
						)
						(
							(or
								(not (StrCmp @str {QUIT}))
								(not (StrCmp @str {LOGOUT}))
								(not (StrCmp @str {EXIT}))
								(not (StrCmp @str {BYE}))
							)
							(self changeState: 3)
						)
						((not (StrCmp @str {CD}))
							(Format @str 8 0)
							(Display 8 14
								p_at 73 14
								p_color 0
								p_font 7
								p_back 0
							)
							(Display 8 190
								p_at 73 14
								p_color 9
								p_font 7
								p_back 0
							)
							(= local149 0)
							(= local147 0)
							(= local146 1)
							(localproc_000c)
						)
					)
				)
			)
		)
	)
)
