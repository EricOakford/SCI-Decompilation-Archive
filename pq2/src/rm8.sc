;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include sci.sh)
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
	[local0 100]
	[str 41]
	eventMessage
	local142
	local143
	local144
	local145
	local146
	local147
	local148
	local149
	local150
	local151
	local152
	local153
	local154
)
(procedure (localproc_000c &tmp newEvent)
	(while ((= newEvent (Event new:)) type?)
		(newEvent dispose:)
	)
	(newEvent dispose:)
)

(procedure (localproc_0031 param1)
	(return
		(if (and (<= 97 param1) (<= param1 122))
			(return (- param1 32))
		else
			(return param1)
		)
	)
)

(procedure (localproc_01c0)
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

(instance rm8 of Rm
	(properties
		picture 8
		style $0007
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(User canInput: 1)
		(Load rsVIEW 9)
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
		(shaw view: 9 loop: 3 cel: 0 posn: 83 150 init: addToPic:)
		(Format @str 8 0)
		(self setScript: rm8Script)
	)
)

(instance rm8Script of Script
	(properties)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said '/book,instruction,cocksucker') (Print 8 1))
					((Said 'look/switch,button,power') (Print 8 2))
					((Said 'look/computer') (Print 8 3))
					((Said 'exit,walk,go,quit') (curRoom newRoom: prevRoomNum))
					((Said 'type,enter,cd,cd') (Print 8 4))
					(
						(or
							(Said 'turn,log<on[/computer,power,button]')
							(Said 'activate,use,logon[/computer]')
							(Said 'flip,press,activate,press[/button,power,switch]')
						)
						(lite1 hide:)
						(lite2 hide:)
						(rm8 setScript: computerScript)
					)
					((Said '[<around,at][/(!*)]') (Print 8 5))
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
			((<= (compCursor x?) 123) (compCursor x: 123))
			((>= (compCursor x?) 236) (compCursor x: 236))
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
					setCycle: Fwd
					init:
				)
				(= local151 5)
			)
			(1
				(= local143 24)
				(while (<= local143 114)
					(Display
						8
						6
						dsCOORD
						73
						local143
						dsFONT
						7
						dsCOLOR
						0
						dsBACKGROUND
						0
					)
					(= local143 (+ local143 10))
				)
				(if (cast contains: fileCursor) (fileCursor dispose:))
				(if (not local146) (self cue:))
			)
			(2
				(= local150 1)
				(Display 8 7 dsCOORD 73 14 dsFONT 7 dsCOLOR 0)
				(Display
					8
					8
					dsCOORD
					73
					14
					dsFONT
					7
					dsCOLOR
					9
					dsBACKGROUND
					0
				)
			)
			(3
				(Display
					8
					9
					dsCOORD
					73
					14
					dsFONT
					7
					dsCOLOR
					0
					dsBACKGROUND
					0
				)
				(Display
					8
					9
					dsCOORD
					73
					15
					dsFONT
					7
					dsCOLOR
					0
					dsBACKGROUND
					0
				)
				(Display
					8
					10
					dsCOORD
					73
					14
					dsFONT
					7
					dsCOLOR
					14
					dsBACKGROUND
					0
				)
				(= newRoomNum prevRoomNum)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 4])
		(switch (event type?)
			(evJOYSTICK
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
								((== (fileCursor x?) 71) (= local152 11) (fileCursor posn: 162 33))
								(else (= local152 1) (fileCursor posn: 71 33))
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
								((== (fileCursor x?) 71) (= local152 20) (fileCursor posn: 162 123))
								(else (= local152 10) (fileCursor posn: 71 123))
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
			(evKEYBOARD
				(if
					(or
						(== (= eventMessage (event message?)) 16384)
						(== eventMessage KEY_F8)
						(== eventMessage KEY_F10)
					)
					(Print 8 11)
					(event claimed: 1)
				)
				(if local150
					(event claimed: 1)
					(= local154 (StrLen @str))
					(cond 
						(
							(and
								(< KEY_SPACE (event message?))
								(< (event message?) 127)
								(< local154 13)
							)
							(StrAt @str local154 (localproc_0031 eventMessage))
							(++ local154)
							(StrAt @str local154 0)
							(Display
								(Format @temp0 {%c} eventMessage)
								dsCOORD
								(compCursor x?)
								(- (compCursor y?) 8)
								dsFONT
								7
								dsCOLOR
								9
								dsBACKGROUND
								0
							)
							(compCursor x: (+ (compCursor x?) 6))
						)
						((and (== JOY_UPLEFT eventMessage) local154)
							(-- local154)
							(StrAt @str local154 0)
							(compCursor x: (- (compCursor x?) 6))
							(Display
								8
								12
								dsCOORD
								(compCursor x?)
								(- (compCursor y?) 8)
								dsFONT
								7
								dsCOLOR
								0
								dsBACKGROUND
								0
							)
						)
						((== eventMessage KEY_RETURN)
							(Display
								8
								13
								dsCOORD
								123
								14
								dsCOLOR
								0
								dsFONT
								7
								dsBACKGROUND
								0
							)
							(Display
								8
								13
								dsCOORD
								123
								15
								dsCOLOR
								0
								dsFONT
								7
								dsBACKGROUND
								0
							)
							(= local154 0)
							(compCursor x: 123)
							(cond 
								(local146
									(cond 
										((not (StrCmp @str {CRIMINAL})) (= local145 3) (= local146 0) (self changeState: 1))
										((not (StrCmp @str {SIERRA})) (= local145 1) (= local146 0) (self changeState: 1))
										((not (StrCmp @str {PERSONNEL}))
											(= local144 2)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										(
										(and (not (StrCmp @str {HOMICIDE})) (== local145 3))
											(= local144 4)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										(
										(and (not (StrCmp @str {VICE})) (== local145 3))
											(= local144 7)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										(
										(and (not (StrCmp @str {BURGLARY})) (== local145 3))
											(= local144 5)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										(
										(and (not (StrCmp @str {FIREARMS})) (== local145 3))
											(= local144 6)
											(= local146 0)
											(= local148 1)
											(self changeState: 1)
										)
										(else (= local145 0) (= local146 0) (self changeState: 1))
									)
									(if local148
										(Display
											8
											14
											dsCOORD
											73
											14
											dsCOLOR
											0
											dsFONT
											7
											dsBACKGROUND
											0
										)
										(Display
											8
											15
											dsCOORD
											73
											14
											dsCOLOR
											9
											dsFONT
											7
											dsBACKGROUND
											0
										)
									)
								)
								(local148
									(= local148 0)
									(cond 
										(
										(and (not (StrCmp @str {ICECREAM})) (== local144 4)) (= local145 local144))
										(
										(and (not (StrCmp @str {PISTACHIO})) (== local144 2)) (= local145 local144) (SolvePuzzle 2 120))
										(
										(and (not (StrCmp @str {MIAMI})) (== local144 7)) (= local145 local144) (SolvePuzzle 2 121))
										(else (Print 8 16 #time 3))
									)
									(self changeState: 1)
								)
								(local149
									(switch local152
										(1 (localproc_01c0 8 17))
										(2 (localproc_01c0 8 18))
										(3 (localproc_01c0 8 19))
										(4 (localproc_01c0 8 20))
										(5
											(localproc_01c0 8 21)
											(localproc_01c0 8 22)
										)
										(6 (localproc_01c0 8 23))
										(7
											(localproc_01c0 8 24)
											(localproc_01c0 8 25)
										)
										(8 (localproc_01c0 8 26))
										(9 (localproc_01c0 8 27))
										(10 (localproc_01c0 8 28))
										(11 (localproc_01c0 8 29))
										(12 (localproc_01c0 8 30))
										(13 (localproc_01c0 8 31))
										(14 (localproc_01c0 8 32))
										(15 (localproc_01c0 8 33))
									)
								)
								(local147
									(switch local145
										(4
											(switch local152
												(1
													(localproc_01c0 8 34)
													(localproc_01c0 8 35)
												)
												(2 (localproc_01c0 8 36))
												(3
													(localproc_01c0 8 37)
													(localproc_01c0 8 38)
												)
												(4 (localproc_01c0 8 39))
												(5 (localproc_01c0 8 40))
												(6
													(localproc_01c0 8 41)
													(localproc_01c0 8 42)
												)
												(7 (localproc_01c0 8 43))
												(11
													(localproc_01c0 8 44)
													(localproc_01c0 8 45)
												)
												(12
													(localproc_01c0 8 46)
													(localproc_01c0 8 47)
												)
												(13 (localproc_01c0 8 48))
												(14 (localproc_01c0 8 49))
												(15 (localproc_01c0 8 50))
												(16 (localproc_01c0 8 51))
											)
										)
										(2
											(switch local152
												(1
													(localproc_01c0 8 52)
													(localproc_01c0 8 53)
												)
												(2
													(localproc_01c0 8 54)
													(localproc_01c0 8 55)
													(localproc_01c0 8 56)
												)
												(3
													(localproc_01c0 8 57)
													(localproc_01c0 8 58)
													(localproc_01c0 8 59)
												)
												(4
													(localproc_01c0 8 60)
													(localproc_01c0 8 61)
													(localproc_01c0 8 62)
												)
												(5
													(localproc_01c0 8 63)
													(localproc_01c0 8 64)
												)
												(6
													(localproc_01c0 8 65)
													(localproc_01c0 8 66)
												)
												(7
													(localproc_01c0 8 67)
													(localproc_01c0 8 68)
												)
												(8
													(localproc_01c0 8 69)
													(localproc_01c0 8 70)
													(localproc_01c0 8 71)
												)
												(9
													(localproc_01c0 8 72)
													(localproc_01c0 8 73)
												)
												(10
													(localproc_01c0 8 74)
													(localproc_01c0 8 75)
													(localproc_01c0 8 76)
													(Bset 56)
												)
												(11
													(localproc_01c0 8 77)
													(localproc_01c0 8 78)
												)
												(12
													(localproc_01c0 8 79)
													(localproc_01c0 8 80)
													(localproc_01c0 8 81)
												)
												(13
													(localproc_01c0 8 82)
													(localproc_01c0 8 83)
													(localproc_01c0 8 84)
													(localproc_01c0 8 85)
												)
												(14
													(localproc_01c0 8 86)
													(localproc_01c0 8 87)
												)
												(15
													(localproc_01c0 8 88)
													(localproc_01c0 8 89)
												)
												(16
													(localproc_01c0 8 90)
													(localproc_01c0 8 91)
												)
												(17 (localproc_01c0 8 92))
											)
										)
										(7
											(switch local152
												(1
													(localproc_01c0 8 93)
													(localproc_01c0 8 94)
												)
												(2
													(localproc_01c0 8 95)
													(localproc_01c0 8 96)
												)
												(3
													(localproc_01c0 8 97)
													(localproc_01c0 8 98)
												)
												(4
													(localproc_01c0 8 99)
													(localproc_01c0 8 100)
												)
												(5
													(localproc_01c0 8 101)
													(localproc_01c0 8 102)
												)
												(6
													(localproc_01c0 8 103)
													(localproc_01c0 8 104)
												)
												(7
													(localproc_01c0 8 105)
													(localproc_01c0 8 106)
												)
												(8
													(localproc_01c0 8 107)
													(localproc_01c0 8 108)
												)
												(9
													(localproc_01c0 8 109)
													(localproc_01c0 8 110)
												)
												(11
													(localproc_01c0 8 111)
													(localproc_01c0 8 112)
												)
												(12
													(localproc_01c0 8 113)
													(localproc_01c0 8 114)
												)
												(13
													(localproc_01c0 8 115)
													(localproc_01c0 8 116)
												)
												(14
													(localproc_01c0 8 117)
													(localproc_01c0 8 118)
												)
												(15
													(localproc_01c0 8 119)
													(localproc_01c0 8 120)
												)
												(16
													(localproc_01c0 8 121)
													(localproc_01c0 8 122)
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
							(Display
								8
								13
								dsCOORD
								123
								14
								dsCOLOR
								0
								dsFONT
								7
								dsBACKGROUND
								0
							)
							(Display
								8
								13
								dsCOORD
								123
								15
								dsCOLOR
								0
								dsFONT
								7
								dsBACKGROUND
								0
							)
							(compCursor x: 123)
							(switch local145
								(0
									(Display
										8
										123
										dsCOORD
										73
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										124
										dsCOORD
										73
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										125
										dsCOORD
										73
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
								)
								(1
									(Display
										8
										126
										dsCOORD
										73
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										127
										dsCOORD
										73
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										128
										dsCOORD
										73
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										129
										dsCOORD
										73
										54
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										130
										dsCOORD
										73
										64
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										131
										dsCOORD
										73
										74
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										132
										dsCOORD
										73
										84
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										133
										dsCOORD
										73
										94
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										134
										dsCOORD
										73
										104
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										(Format @local0 8 135)
										dsCOORD
										73
										114
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										(Format @local0 8 136)
										dsCOORD
										160
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										(Format @local0 8 137)
										dsCOORD
										160
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										(Format @local0 8 138)
										dsCOORD
										160
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										(Format @local0 8 139)
										dsCOORD
										160
										54
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										(Format @local0 8 140)
										dsCOORD
										160
										64
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(= local149 1)
									(= local152 1)
									(fileCursor view: 9 loop: 1 posn: 71 32 init:)
								)
								(2
									(Display
										(Format @local0 8 141)
										dsCOORD
										73
										24
										dsCOLOR
										9
										dsFONT
										7
									)
									(Display
										(Format @local0 8 142)
										dsCOORD
										73
										34
										dsCOLOR
										9
										dsFONT
										7
									)
									(Display
										(Format @local0 8 143)
										dsCOORD
										73
										44
										dsCOLOR
										9
										dsFONT
										7
									)
									(Display
										(Format @local0 8 144)
										dsCOORD
										73
										54
										dsCOLOR
										9
										dsFONT
										7
									)
									(Display
										(Format @local0 8 145)
										dsCOORD
										73
										64
										dsCOLOR
										9
										dsFONT
										7
									)
									(Display
										(Format @local0 8 146)
										dsCOORD
										73
										74
										dsCOLOR
										9
										dsFONT
										7
									)
									(Display 8 147 dsCOORD 73 84 dsCOLOR 9 dsFONT 7)
									(Display 8 148 dsCOORD 73 94 dsCOLOR 9 dsFONT 7)
									(Display 8 149 dsCOORD 73 104 dsCOLOR 9 dsFONT 7)
									(Display 8 150 dsCOORD 73 114 dsCOLOR 9 dsFONT 7)
									(Display 8 151 dsCOORD 155 24 dsCOLOR 9 dsFONT 7)
									(Display 8 152 dsCOORD 155 34 dsCOLOR 9 dsFONT 7)
									(Display 8 153 dsCOORD 155 44 dsCOLOR 9 dsFONT 7)
									(Display 8 154 dsCOORD 155 54 dsCOLOR 9 dsFONT 7)
									(Display 8 155 dsCOORD 155 64 dsCOLOR 9 dsFONT 7)
									(Display 8 156 dsCOORD 155 74 dsCOLOR 9 dsFONT 7)
									(Display 8 157 dsCOORD 155 84 dsCOLOR 9 dsFONT 7)
									(= local147 1)
									(= local152 1)
									(fileCursor view: 9 loop: 1 posn: 71 32 init:)
								)
								(3
									(Display
										8
										158
										dsCOORD
										73
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										159
										dsCOORD
										73
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										160
										dsCOORD
										73
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										161
										dsCOORD
										73
										54
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
								)
								(4
									(Display
										8
										162
										dsCOORD
										73
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										163
										dsCOORD
										73
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										164
										dsCOORD
										73
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										165
										dsCOORD
										73
										54
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										166
										dsCOORD
										73
										64
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										167
										dsCOORD
										73
										74
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										168
										dsCOORD
										73
										84
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										169
										dsCOORD
										158
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										170
										dsCOORD
										158
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										171
										dsCOORD
										158
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										172
										dsCOORD
										158
										54
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										173
										dsCOORD
										158
										64
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										174
										dsCOORD
										158
										74
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(= local147 1)
									(= local152 1)
									(fileCursor view: 9 loop: 1 posn: 71 32 init:)
								)
								(7
									(Display
										8
										175
										dsCOORD
										73
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										176
										dsCOORD
										73
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										177
										dsCOORD
										73
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										178
										dsCOORD
										73
										54
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										179
										dsCOORD
										73
										64
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										180
										dsCOORD
										73
										74
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										181
										dsCOORD
										73
										84
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										182
										dsCOORD
										73
										94
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										183
										dsCOORD
										73
										104
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										184
										dsCOORD
										154
										24
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										185
										dsCOORD
										154
										34
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										186
										dsCOORD
										154
										44
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										187
										dsCOORD
										154
										54
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										188
										dsCOORD
										154
										64
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
									)
									(Display
										8
										189
										dsCOORD
										154
										74
										dsCOLOR
										9
										dsFONT
										7
										dsBACKGROUND
										0
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
							(Display
								8
								14
								dsCOORD
								73
								14
								dsCOLOR
								0
								dsFONT
								7
								dsBACKGROUND
								0
							)
							(Display
								8
								190
								dsCOORD
								73
								14
								dsCOLOR
								9
								dsFONT
								7
								dsBACKGROUND
								0
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
