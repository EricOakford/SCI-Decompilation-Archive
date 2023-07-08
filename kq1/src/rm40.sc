;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm40 0
)

(local
	local0
)
(procedure (NoMoreGuesses)
	(Print 40 47)
)

(procedure (localproc_10fc)
	(return
		(if
			(and
				(Btst fGnomeAttention)
				(== (gnome view?) 111)
				(== (gnome loop?) 1)
			)
			((ScriptID 0 21) number: 92 loop: 1 init: play:)
			(++ gnomeNameGuesses)
			(if (or (Btst fGotKey) (Btst fGotBeans) (> gnomeNameGuesses 3))
				(NoMoreGuesses)
			else
				(Printf 40 48 &rest)
				(switch gnomeNameGuesses
					(2
						(Print 40 49)
					)
					(1
						(Print 40 50)
					)
				)
				(if (>= gnomeNameGuesses 3)
					(gnome setScript: failGuess)
				)
			)
			(return TRUE)
		else
			(if (cast contains: gnome)
				(if (Btst fInvisible)
					(Print 40 29)
				else
					(Print 40 30)
				)
			)
			(return FALSE)
		)
	)
)

(instance rm40 of Room
	(properties
		picture 40
		horizon 86
		north 41
		east 39
		south 25
		west 33
	)
	
	(method (init)
		(LoadMany VIEW 111 110 240)
		(LoadMany SOUND 92 74)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(addToPics
			add: goldPile strawPile spinningWheel
			eachElementDo: #init
			doit:
		)
		(= local0 0)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 319 (ego x?) 178) (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 319 (ego x?) 52) 188)
			)
			(west
				(ego posn: 3 (proc0_17 155 (ego y?) 121))
			)
			(east (ego x: 317))
			(else  (ego posn: 3 137))
		)
		(ego init:)
		(NormalEgo)
		(hole init:)
		(bush init:)
		(if (and (not (Btst fGotKey)) (not (Btst fGotBeans)))
			(gnome setScript: gnomeWhittle)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			((and script (!= script gnomeWhittle))
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
			((and (& (ego onControl:) cLBLUE) (not local0))
				(= local0 1)
				(Print 40 2)
			)
			((and (not (& (ego onControl:) cLBLUE)) (== local0 1))
				(= local0 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((MouseClaimed event 36 158 70 176)
				(Print 40 3)
			)
			((MouseClaimed event 28 0 165 131)
				(Print 40 4)
			)
			((Said 'look,check/boulder')
				(if (< (GetDistance (ego x?) (ego y?) 53 167) 30)
					(Print 40 3)
				else
					(Print 40 4)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing,building,ceder]')
						(Print 40 4)
					)
					((Said '/leaf')
						(Print 40 5)
					)
					((Said '/hay,(heap<hay)')
						(strawPile doVerb: verbLook)
					)
					((Said '/gold,dust,(heap<gold,dust)')
						(goldPile doVerb: verbLook)
					)
					((Said '/wheel,(wheel<spinning)')
						(spinningWheel doVerb: verbLook)
					)
					((Said '/root')
						(Print 40 6)
					)
					((Said '/hole')
						(Print 40 7)
					)
					((Said '/man,gnome')
						(cond 
							((not (cast contains: gnome))
								(Print 40 8)
							)
							((== (gnome view?) 111)
								(if (== (gnome loop?) 2)
									(Print 40 9)
								else
									(Print 40 10)
								)
							)
							(else
								(Print 40 11)
							)
						)
					)
				)
			)
			((or (Said 'kill,attack') (Said 'throw,use/dagger,boulder,shot'))
				(if (cast contains: gnome)
					(Print 40 12)
				else
					(Print 40 13)
				)
			)
			((Said 'get,take>')
				(cond 
					((Said '/hay,(heap<hay)')
						(Print 40 14)
					)
					((Said '/gold,dust,(heap<gold,dust)')
						(Print 40 15)
					)
					((Said '/wheel,(wheel<spinning)')
						(Print 40 16)
					)
				)
			)
			((or (Said 'spin[/hay][/gold<in]') (Said 'spin/gold,wheel') (Said 'make/gold'))
				(Print 40 17)
			)
			((Said 'enter/building[<gnome]')
				(Print 40 2)
			)
		)
	)
	
	(method (notify param1)
		(if (cast contains: gnome)
			(localproc_10fc 40 0)
		else
			(Printf 40 1 param1)
		)
	)
)

(instance gnome of Actor
	(properties
		x 200
		y 122
		noun '/gnome,man'
		view 111
		priority 9
		signal fixPriOn
		illegalBits $0000
	)
	
	(method (doit &tmp distToGnome)
		(super doit: &rest)
		(= distToGnome (ego distanceTo: gnome))
		(cond
			(
				(and
					(not (Btst fGotBeans))
					(not (Btst fGotKey))
					(not (Btst fInvisible))
					(not
						(and (gnome script:) (!= (gnome script:) gnomeWhittle))
					)
					(< distToGnome 40)
					(> (ego y:) 128)
					(== (gnome loop:) 2)
				)
				(gnomeTalk start: 0)
				(gnome setScript: gnomeTalk)
			)
			(
				(and
					(not (Btst fGotBeans))
					(not (Btst fGotKey))
					(not
						(and (gnome script:) (!= (gnome script:) gnomeWhittle))
					)
					(or (> distToGnome 70) (Btst fInvisible))
					(< (gnome loop:) 2)
				)
				(gnomeTalk start: 4)
				(gnome setScript: gnomeTalk)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'kill,attack') (Said 'throw,use/dagger,boulder,shot'))
				(if (cast contains: gnome)
					(Print 40 12)
				else
					(Print 40 13)
				)
			)
			((or (Said 'look,check[/gnome,man,room]') (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(if (== (gnome view?) 111)
					(if (== (gnome loop?) 2)
						(Print 40 9)
					else
						(Print 40 10)
					)
				else
					(Print 40 11)
				)
			)
			((Said 'capture,get,take,take/gnome,man')
				(Print 40 18)
			)
			((Said '/rumpelstilskin')
				(if (Btst fGnomeAttention)
					(if (not (localproc_10fc 40 19))
						(event claimed: FALSE)
					)
				else
					(Print 40 20)
				)
			)
			((Said '/mikel')
				(if (Btst fGnomeAttention)
					(if (or (Btst fGotKey) (Btst fGotBeans))
						(if (!= gnomeNameGuesses 1)
							(++ gnomeNameGuesses)
							(Print 40 21)
						else
							(Print 40 22)
						)
					else
						(NoMoreGuesses)
					)
				else
					(Print 40 20)
				)
			)
			((Said '/nikslitselpmur')
				(if (Btst fGnomeAttention)
					(self setScript: goodGuess)
				else
					(Print 40 20)
				)
			)
			((or (Said 'kiss/gnome,man') (Said 'hug/gnome,man'))
				(Print 40 23)
			)
			((or (Said 'talk,speak') (Said 'say/hello') (Said 'hello'))
				(cond 
					((curRoom script?)
						(CantDo)
					)
					((Btst fInvisible)
						(Print 40 24)
					)
					((and (< (ego distanceTo: gnome) 60) (> (ego y?) 128))
						(if (and (Btst fGnomeAsks) (Btst fGnomeAttention))
							(Print 40 25)
						else
							(gnomeTalk start: 0)
							(gnome setScript: gnomeTalk)
						)
					)
					(else
						(Print 40 26)
					)
				)
			)
			(
			(and (!= (event type?) mouseDown) (Btst fGnomeAttention))
				(theGame handleEvent: event)
				(if (not (event claimed?))
					(cond 
						((and (== (gnome view?) 111) (== (gnome loop?) 1))
							(++ gnomeNameGuesses)
							((ScriptID 0 21) number: 92 loop: 1 init: play:)
							(switch gnomeNameGuesses
								(1
									(Print 40 27)
								)
								(2
									(Print 40 28)
								)
								(3
									(self setScript: failGuess)
								)
								(else
									(NoMoreGuesses)
								)
							)
							(event claimed: TRUE)
						)
						((cast contains: gnome)
							(if (Btst fInvisible)
								(Print 40 29)
							else
								(Print 40 30)
							)
							(event claimed: TRUE)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
			)
		)
	)
)

(instance gnomeWhittle of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gnome init: setLoop: 2 cycleSpeed: 1)
				(self cue:)
			)
			(1 (gnome setCycle: EndLoop self))
			(2 (= cycles (Random 1 5)))
			(3 (self changeState: 1))
		)
	)
)

(instance goodGuess of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
					(and
						(curRoom script:)
						(== (ego view:) (if (Btst fLittleEgo) 23 else 16))
					)
					((curRoom script:) cue:)
					(= cycles 7)
				else
					(self cue:)
				)
			)
			(1
				((ScriptID 0 21) number: 74 loop: 1 init: play:) ; gameSound
				(Print 40 31) ; "GNOME: "That's right! Outstanding! I didn't think you were THAT clever.""
				(Print 40 32) ; "GNOME: "As a reward for your sharp intellect, here are some beans. They're no ordinary beans, but it's up to you to find out why.""
				(Print 40 33) ; "GNOME: "Somebody as smart as yourself should have no problem at all. <giggle>""
				(if (< (ego distanceTo: gnome) 15)
					(self changeState: 2)
				else
					(Print 40 34) ; "GNOME: "Well, step over here so I can give them to you.""
					(self cue:)
				)
			)
			(2
				(self setScript: approachGnome self)
			)
			(3
				((ScriptID 0 21) number: 105 loop: 1 init: play:) ; gameSound
				(SolvePuzzle fGuessedGnomeName (- 5 gnomeNameGuesses))
				(SolvePuzzle fGotBeans 4)
				(ego get: iBeans)
				(if (== (ego onControl: origin) cBLUE)
					(self setScript: gnomeWaitLeave self)
				else
					(self setScript: gnomeLeaves self)
				)
			)
			(4
				(self dispose:)
			)
		)
	)
)

(instance failGuess of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
					(and
						(curRoom script:)
						(== (ego view:) (if (Btst fLittleEgo) 23 else 16))
					)
					((curRoom script:) cue:)
					(= cycles 7)
				else
					(self cue:)
				)
			)
			(1
				(Print 40 35) ; "GNOME: "You didn't guess my name, but I am still going to help you, Sir Graham.""
				(Print 40 36) ; "GNOME: "Take this golden key. It will aid you on your quest, Sir Graham, but it's up to you to find out how!""
				(if (< (ego distanceTo: gnome) 15)
					(self changeState: 2)
				else
					(Print 40 37) ; "GNOME: "Well, step over here so I can give it to you.""
					(self cue:)
				)
			)
			(2
				(self setScript: approachGnome self)
			)
			(3
				((ScriptID 0 21) number: 105 loop: 1 init: play:) ; gameSound
				(SolvePuzzle fGotKey 3)
				(ego get: iKey)
				(if (== (ego onControl: origin) cBLUE)
					(gnome setScript: gnomeWaitLeave)
				else
					(gnome setScript: gnomeLeaves)
				)
				(self dispose:)
			)
		)
	)
)

(instance approachGnome of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setAvoider: Avoider
					setMotion: MoveTo (gnome x?) (+ (gnome y?) 10) self
				)
			)
			(1
				(ego setAvoider: 0 setHeading: 0)
				(= cycles 5)
			)
			(2
				(Print 40 38)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance gnomeWaitLeave of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 40 39)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 155 136 self
				)
			)
			(1
				(ego setMotion: MoveTo 175 155 self)
			)
			(2
				(Print 40 40)
				(NormalEgo)
				(gnome
					view: 110
					cycleSpeed: 0
					ignoreControl:
					ignoreActors:
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: MoveTo 158 140 self
				)
			)
			(3
				(gnome setPri: -1 setMotion: MoveTo 100 110 self)
			)
			(4
				(gnome dispose:)
				(HandsOn)
			)
		)
	)
)

(instance gnomeLeaves of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gnome
					view: 110
					cycleSpeed: 0
					ignoreControl:
					ignoreActors:
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: MoveTo 128 122 self
				)
			)
			(1
				(gnome setPri: -1 setMotion: MoveTo 90 108 self)
			)
			(2
				(gnome dispose:)
				(HandsOn)
			)
		)
	)
)

(instance gnomeTalk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fGnomeAttention)
				(gnome loop: 1 cel: 0)
				(= cycles 2)
			)
			(1
				(gnome loop: 0 setCycle: EndLoop self)
			)
			(2
				(if (Btst fMetGnome)
					(Print 40 41)
					(Print 40 25)
				else
					(Print 40 42)
					(Bset fMetGnome)
				)
				(gnome cel: 0 setCycle: EndLoop self)
			)
			(3
				(if (not (Btst fGnomeAsks))
					(Print 40 43)
					(Bset fGnomeAsks)
				)
				(gnome setLoop: 1)
				(HandsOn)
				(self dispose:)
			)
			(4
				(gnome loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(5
				(Print 40 44)
				(gnome loop: 2 setCycle: Forward)
				(self dispose:)
			)
			(6
				(Print 40 45)
			)
			(7
				(Print 40 46)
			)
		)
	)
)

(instance spinningWheel of PicView
	(properties
		x 242
		y 133
		noun '/wheel'
		nsTop 103
		nsLeft 226
		nsBottom 130
		nsRight 256
		description {spinning wheel}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 500
		shiftClick 1
		view 240
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(Print 40 51)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance goldPile of PicView
	(properties
		x 224
		y 143
		noun '/gold,dust'
		nsTop 129
		nsLeft 212
		nsBottom 142
		nsRight 238
		description {pile of gold}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 500
		shiftClick 1
		view 240
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 40 52)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance strawPile of PicView
	(properties
		x 265
		y 140
		noun '/heap<hay,hay'
		nsTop 130
		nsLeft 254
		nsBottom 140
		nsRight 277
		description {pile of straw}
		sightAngle 360
		closeRangeDist 320
		longRangeDist 500
		shiftClick 1
		view 240
		cel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(Print 40 53)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance hole of NewFeature
	(properties
		x 122
		y 98
		noun '/hole,door,doorway'
		nsTop 78
		nsLeft 111
		nsBottom 118
		nsRight 133
		description {hole in tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This hole, carved into the side of the rock, is the entrance to the gnome's home.__It's too dark inside to make out any details, and the gnome probably prefers it that way.}
	)
)

(instance bush of NewFeature
	(properties
		x 28
		y 165
		noun '/bush'
		nsTop 153
		nsLeft 7
		nsBottom 178
		nsRight 49
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several nice bushes and plants surround the gnome's home.}
	)
)
