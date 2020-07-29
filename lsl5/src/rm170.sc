;;; Sierra Script 1.0 - (do not remove this comment)
(script# 170)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use PrintD)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm170 0
)

(local
	local0
	eightTrackOn
)
(instance rm170 of LLRoom
	(properties
		lookStr {You are so proud of this, your humble workshop.}
		picture 170
		east 160
	)
	
	(method (init)
		(self setRegions: rgHollywood)
		(ego init: normalize: illegalBits: 8)
		(switch prevRoomNum
			(east 0)
			(else 
				(HandsOn)
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(super init:)
		(if
			(not
				(if (or (ego has: 2) (ego has: 3)) else (ego has: 4))
			)
			(camcorderTapes init: approachVerbs: 3)
		)
		(door init: approachVerbs: 3)
		(degausser init: approachVerbs: 4)
		(drawer1 init: approachVerbs: 3)
		(drawer2 init: approachVerbs: 3)
		(drawer3 init: approachVerbs: 3)
		(cabinet init:)
		(sterileBarrel init: approachVerbs: 3 4)
		(videoMonitor1 init:)
		(videoMonitor2 init:)
		(drain init:)
		(boxes init:)
		(videotapes1 init:)
		(videotapes2 init:)
		(videotapes3 init:)
		(videotapes4 init:)
		(videotapes5 init:)
		(videotapes6 init:)
		(videotapes7 init:)
		(lightWest init:)
		(lightEast init:)
		(dice init:)
		(eightTrack init: approachVerbs: 3)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						319 116
						216 116
						204 113
						204 108
						209 97
						198 97
						190 101
						36 101
						11 120
						114 120
						109 125
						60 133
						14 129
						3 137
						3 186
						156 164
						239 158
						242 175
						235 189
						315 165
						315 145
						284 143
						255 128
						283 119
						319 119
					yourself:
				)
		)
	)
)

(instance sOpenDrawer of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic2 number: 171 setLoop: 1 play:)
				(ego
					view: 171
					setLoop: 0
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(1 (= cycles 3))
			(2
				(switch register
					(3
						(TimePrint 170 0
							#at -1 150)
						)
					(2
						(TimePrint 170 1
							#at -1 150
						)
					)
					(1
						(if (Btst fGotCharger)
							(TimePrint 170 2 #at -1 150)
						else
							(TimePrint 170 3 #at -1 150)
							(SolvePuzzle 8 fGotCharger)
							(TimePrint 170 4 #at -1 150)
							(ego get: iCharger)
						)
					)
				)
				(= cycles 3)
			)
			(3
				(theMusic2 number: 171 setLoop: 1 play:)
				(ego setCycle: BegLoop self)
			)
			(4
				(ego loop: 3 normalize: illegalBits: cCYAN)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sTapes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 171
					setLoop: 1
					setCel: 0
					posn: 237 158
					setCycle: CycleTo 3 1 self
				)
				(egoBody init:)
			)
			(1
				(camcorderTapes dispose:)
				(ego setCycle: EndLoop self)
			)
			(2 (= cycles 5))
			(3
				(SolvePuzzle 6 fGotTapes)
				(ego get: 2 3 4)
				(= cycles 5)
			)
			(4
				(ego setLoop: 2 posn: 236 184)
				(TimePrint 170 5)
				(TimePrint 170 6 #at -1 185)
				(egoBody dispose:)
				(ego normalize: illegalBits: cCYAN)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sDegaussTape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 1)
			)
			(1
				(if (not register)
					(TimePrint 170 7)
					(self changeState: 3)
				else
					(theMusic2 number: 172 setLoop: -1 play:)
					(SolvePuzzle 2)		;EO: This didn't have a flag assigned to it.
					(Bset fErasedTapes)	;This can't be in SolvePuzzle, since we need to give points for each tape.
										;However, the flag will be set to fix an oversight in room 385.
					(if (not local0)
						(TimePrint 170 8)
						(TimePrint 170 9 #at -1 185)
						(= local0 1)
						(= cycles 1)
					else
						(= ticks 120)
					)
				)
			)
			(2
				(theMusic2 stop:)
				(CheckTapeState tapeERASED register)
				(= cycles 1)
			)
			(3
				(ego
					setMotion: MoveTo (- (ego x?) 15) (+ (ego y?) 5) self
				)
			)
			(4
				(ego setLoop: 2 normalize: illegalBits: 8)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance camcorderTapes of View
	(properties
		x 249
		y 181
		description {the camcorder tapes}
		sightAngle 40
		approachX 236
		approachY 184
		lookStr {You've found them! These are the tapes you need for your new Pocket Protector Camcorder.}
		view 170
		priority 15
		signal $4010
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sTapes)
			)
			(verbUse
				(switch theItem
					(iCamcorder
						(TimePrint 170 10)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance egoBody of View
	(properties
		x 237
		y 185
		view 171
		loop 2
		priority 10
		signal $0010
	)
)

(instance door of Door
	(properties
		x 292
		y 116
		heading 135
		description {the lobby door}
		approachX 240
		approachY 117
		lookStr {The door to the lobby seems oh, so familiar to you. Probably because you entered this room through it! Your "The Girls of Videotape Technician's Magazine" calendar hangs on the back, open to Miss October, who (in your opinion) has the best "reels" of the bunch!}
		view 171
		loop 3
		entranceTo 160
		moveToX 285
		moveToY 116
		enterType 0
		exitType 0
	)
	
	(method (init)
		(ego ignoreControl: cCYAN)
		(super init: &rest)
		(self startUpd: setPri: 9)
	)
	
	(method (open)
		(self setPri: 9)
		(ego illegalBits: 0)
		(super open:)
	)
	
	(method (close)
		(super close:)
		(self setPri: 8)
		(ego observeControl: cCYAN cWHITE)
	)
)

(instance degausser of Feature
	(properties
		x 162
		y 74
		nsTop 65
		nsLeft 151
		nsBottom 84
		nsRight 173
		description {the degausser}
		sightAngle 40
		lookStr {Your degausser, which you use to degauss tapes, rests on the workbench.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 170 11)
				(TimePrint 170 12 #at -1 185)
			)
			(verbUse
				(if (OneOf theItem iTapeA iTapeB iTapeC)
					(if ((Inventory at: theItem) state?)
						(curRoom setScript: sDegaussTape 0 0)
					else
						(curRoom setScript: sDegaussTape 0 theItem)
					)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance drawer1 of Feature
	(properties
		x 52
		y 88
		nsTop 84
		nsLeft 37
		nsBottom 93
		nsRight 68
		description {the drawer}
		sightAngle 40
		approachX 57
		approachY 109
		lookStr {It looks like a closed drawer.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sOpenDrawer 0 1)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance drawer2 of Feature
	(properties
		x 99
		y 88
		nsTop 84
		nsLeft 82
		nsBottom 93
		nsRight 117
		description {the drawer}
		sightAngle 40
		approachX 104
		approachY 110
		lookStr {It looks like a closed drawer.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sOpenDrawer 0 2)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance drawer3 of Feature
	(properties
		x 145
		y 88
		nsTop 84
		nsLeft 129
		nsBottom 93
		nsRight 162
		description {the drawer}
		sightAngle 40
		approachX 151
		approachY 110
		lookStr {It looks like a closed drawer.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sOpenDrawer 0 3)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cabinet of Feature
	(properties
		x 98
		y 44
		nsTop 30
		nsLeft 38
		nsBottom 58
		nsRight 159
		description {the cabinet}
		sightAngle 40
		lookStr {You have no idea what's inside these cabinets since you are too short to see inside.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 170 13)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sterileBarrel of Feature
	(properties
		x 222
		y 44
		nsTop 22
		nsLeft 204
		nsBottom 66
		nsRight 240
		description {your barrel of sterilizer}
		sightAngle 40
		lookStr {This barrel contains the sterilizing solution that you use on all incoming video tapes before rewinding them and distributing them to the members of the ASHV Audition Team.}
	)
	
	(method (doVerb theVerb theItem &tmp [str 100])
		(switch theVerb
			(verbDo
				(SolvePuzzle 1 fSterlizedHands)
				(TimePrint 170 14)
			)
			(verbTalk
				(TimePrint 170 15)
			)
			(verbUse
				(Format @str 170 16
					((Inventory at: theItem) description?) 37
				)
				(TimePrint @str)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance videoMonitor1 of Feature
	(properties
		x 29
		y 68
		nsTop 54
		nsLeft 14
		nsBottom 82
		nsRight 44
		description {the video monitor}
		sightAngle 40
		lookStr {You use this monitor every day to preview the incoming submissions, making sure they're explicit enough to pass on to the official members of the Submissions Team.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 170 17)
			)
			(verbUse
				(if (OneOf theItem iTapeA iTapeB iTapeC)
					(TimePrint 170 18)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance videoMonitor2 of Feature
	(properties
		x 286
		y 168
		nsTop 148
		nsLeft 254
		nsBottom 189
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb)
		(videoMonitor1 doVerb: &rest)
	)
)

(instance drain of Feature
	(properties
		x 149
		y 1124
		z 1000
		nsTop 119
		nsLeft 133
		nsBottom 130
		nsRight 166
		description {the floor drain}
		sightAngle 40
		lookStr {You often feel this is where your life is headed.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 170 19)
				(TimePrint 170 20 #at -1 185)
			)
			(verbUse
				(TimePrint 170 21)
				(TimePrint 170 22 #at -1 20)
				(TimePrint 170 22)
				(TimePrint 170 22 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance boxes of Feature
	(properties
		x 84
		y 156
		nsTop 123
		nsLeft 49
		nsBottom 189
		nsRight 120
		description {the boxes}
		sightAngle 40
		lookStr {You have no use for empty boxes.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 170 23)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance videotapes1 of Feature
	(properties
		x 10
		y 170
		nsTop 151
		nsBottom 189
		nsRight 21
		description {some videotapes}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 170 24)
			)
			(verbLook
				(TimePrint 170 25)
				(TimePrint 170 26 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance videotapes2 of Feature
	(properties
		x 116
		y 71
		nsTop 63
		nsLeft 89
		nsBottom 79
		nsRight 143
		sightAngle 40
	)
	
	(method (doVerb)
		(videotapes1 doVerb: &rest)
	)
)

(instance videotapes3 of Feature
	(properties
		x 55
		y 115
		nsTop 101
		nsLeft 6
		nsBottom 130
		nsRight 104
		sightAngle 40
	)
	
	(method (doVerb)
		(videotapes1 doVerb: &rest)
	)
)

(instance videotapes4 of Feature
	(properties
		x 169
		y 153
		nsTop 118
		nsLeft 109
		nsBottom 188
		nsRight 229
		sightAngle 40
	)
	
	(method (doVerb)
		(videotapes1 doVerb: &rest)
	)
)

(instance videotapes5 of Feature
	(properties
		x 227
		y 96
		nsTop 79
		nsLeft 204
		nsBottom 113
		nsRight 250
		sightAngle 40
	)
	
	(method (doVerb)
		(videotapes1 doVerb: &rest)
	)
)

(instance videotapes6 of Feature
	(properties
		x 304
		y 114
		nsTop 82
		nsLeft 289
		nsBottom 146
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb)
		(videotapes1 doVerb: &rest)
	)
)

(instance videotapes7 of Feature
	(properties
		x 103
		y 16
		nsTop 3
		nsLeft 41
		nsBottom 29
		nsRight 166
		sightAngle 40
	)
	
	(method (doVerb)
		(videotapes1 doVerb: &rest)
	)
)

(instance lightWest of Feature
	(properties
		x 24
		y 32
		nsTop 23
		nsLeft 8
		nsBottom 41
		nsRight 41
		description {the lamp}
		sightAngle 40
		lookStr {It's just hanging around here... rather like you, Larry!}
	)
	
	(method (onMe)
		(if (super onMe: &rest)
		else
			(lightEast onMe: &rest)
		)
	)
)

(instance lightEast of Feature
	(properties
		x 178
		y 29
		nsTop 19
		nsLeft 160
		nsBottom 39
		nsRight 196
		sightAngle 40
	)
)

(instance dice of Feature
	(properties
		x 190
		y 127
		z 52
		nsTop 65
		nsLeft 180
		nsBottom 85
		nsRight 200
		description {the fuzzy dice}
		sightAngle 40
		lookStr {Your sole attempt to decorate your work space is one-half of a pair of fuzzy dice, left over from your '74 Bug.}
	)
)

(instance eightTrack of Prop
	(properties
		x 54
		y 77
		description {your 8-track player}
		sightAngle 40
		lookStr {Your trusty Strombug Carlton 8-track cartridge tape player rests on your workbench. Although lately you've had trouble finding new cartridges, it still really belts out the tunes.}
		view 170
		cel 2
	)
	
	(method (init)
		(= eightTrackOn FALSE)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp ret)
		(switch theVerb
			(verbDo
				(SolvePuzzle 5 fPlayed8Tracks)
				(if (not eightTrackOn)
					(= ret
						(PrintD {Play which 8-track cartridge?}
							#new
							#button {__The Larry Song__} 100
							#button { Record des Rever_} 640
							#new
							#button {__Desmond's Theme__} 410
							#button {__Silas Scruemall__} 150
							#new
							#button {___Closing Theme___} 462
							#button {___Hard Rock Me___} 500
							#new
							#button {___Hard Rock You__} 501
							#button {____Hard Rock It____} 502
							#new
							#button {__Michelle's Song__} 535
							#button {______Shill This______} 620
							#new
							#button {______Saxy Sex______} 345
							#button {On The Boardwalk} 700
							#new
							#button {< Next Screen >} -1 #x 70 #y 10
							#new
							#button {< Eject >} 0 #x 95
						)
					)
				else
					(= ret
						(PrintD {Play which 8-track cartridge?}
							#new
							#button { A Studio Session_} 660
							#button {__Gettin' Some Air__} 663
							#new
							#button {____Nasty Girlz_____} 738
							#button {____Whoz On Top?____} 740
							#new
							#button {__Tramp's Casino__} 710
							#button {____Ivana Skate____} 790
							#new
							#button {_____K-RAP Rap_____} 800
							#button {_____K-RAP Talk_____} 801
							#new
							#button {____K-RAP Rock_____} 802
							#button {___Doc'll Pulliam____} 905
							#new
							#button {__Chi Chi a Me Me__} 910
							#button {____Salsa This_____} 920
							#new
							#button {< Previous Screen >} -1 #x 70 #y 10
							#new
							#button {< Eject >} 0 #x 95
						)
					)
				)
				(switch ret
					(-1
						(= eightTrackOn (- 1 eightTrackOn))
						(StartTimer 1 0 self)
					)
					(0
						(theMusic number: 160 setLoop: -1 play:)
					)
					(else 
						(theMusic number: ret setLoop: -1 play:)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self doVerb: verbLook)
	)
)

(instance funSound of Sound)
