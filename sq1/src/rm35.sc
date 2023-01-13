;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use DScript)
(use Talker)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm35 0
)

(local
	[str 250]
	local250
	local251
	theWorker
	local253
	egoSitting
	theCel
	local256
	local257
	evX
	evY
)
(instance rm35 of SQRoom
	(properties
		lookStr {This place is most interesting, indeed. A mixture of both old and new technologies makes you wonder about its origin. 
		Or at least about its tech support. On one side of the chamber is a primitive steam generator. 
		Its pistons, pounding rhythmically, turn steam into useful energy. On the other side is what appears to be a computer console.}
		picture 35
		style $8009
	)
	
	(method (init)
		(LoadMany VIEW 61 430 135 427 25 328)
		(Load SCRIPT DPATH)
		(LoadMany SOUND 436 462 461 460 413)
		(HandsOff)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 170 262 174 253 170 228 170 214 166 213 166
						190 155 189 148 208 135 198 131 178 138 152 138 139 130 140 121
						108 121 103 148 186 189
					yourself:
				)
		)
		(super init:)
		(ship init: approachVerbs: verbDo)
		(ladder init: approachVerbs: verbDo)
		(computer init:)
		(chair init:)
		(ledge init:)
		(portal init:)
		(computerPipes init:)
		(windows init:)
		(skimmerStand init:)
		(machinery init:)
		(steamBlowers init:)
		(bigPipes init:)
		(smallPipes init:)
		(dialPipes init:)
		(floor init:)
		(lightBulb3 init: setCycle: Forward cycleSpeed: 5)
		(lightBulb2 init: setCycle: Forward cycleSpeed: 8)
		(lightBulb1 init: setCycle: Forward cycleSpeed: 11)
		(shaft init: setCycle: Reverse cycleSpeed: 8)
		(screen init: stopUpd:)
		(whistle init: setScript: blowWhistle 0 (Random 3 7))
		(steam init: hide:)
		(steam2 init: hide:)
		(ego init: view: 1 posn: 317 249 ignoreControl: cWHITE)
		(= egoSitting 0)
		(HandsOff)
		(self setScript: egoEnterRoom)
		(leader init: hide:)
		(worker init: hide:)
		(worker setScript: workerDoYourThing)
		(= local253 0)
		(theMusic number: 463 loop: -1 play:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script 0)
			((and (ego mover?) (== egoSitting 1))
				(= evX ((User curEvent?) x?))
				(= evY ((User curEvent?) y?))
				(curRoom setScript: standUpPoly)
			)
			((& (ego onControl:) cYELLOW) (if (!= (ego priority?) 13) (ego setPri: 13)))
			((== egoSitting 1) 0)
			((& (ego signal?) cRED) (ego setPri: -1))
		)
	)
	
	(method (newRoom n)
		(theMusic fade: loop: 0)
		(super newRoom: n)
	)
)

(instance egoEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: 7 setMotion: MoveTo 170 170 self)
			)
			(1
				(ego setLoop: -1 observeControl: cWHITE)
				(Print 35 0)
				(leader setScript: leaderEnterRoom)
				(self dispose:)
			)
		)
	)
)

(instance workerDoYourThing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 300 600)))
			(1
				(= theWorker worker)
				(= cycles 3)
			)
			(2
				(worker
					show:
					x: 0
					y: 139
					setLoop: 8
					cel: 0
					setPri: 4
					setMotion: MoveTo 75 139 self
				)
				(hoverThing init: setLoop: 5 setCycle: Reverse)
			)
			(3 (worker setCycle: EndLoop self))
			(4
				(worker setLoop: 9 cel: 0 setCycle: Oscillate 3 self)
			)
			(5
				(worker setLoop: 8 setCycle: BegLoop self)
			)
			(6
				(worker setMotion: MoveTo 0 139 self)
			)
			(7
				(worker y: 0 hide:)
				(hoverThing dispose:)
				(= state -1)
				(= cycles 30)
			)
		)
	)
)

(instance standUpPoly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 183 y: 131 setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(1
				(NormalEgo 2 1 61)
				(ego observeControl: cWHITE)
				(= egoSitting 0)
				(HandsOn)
				(if
					(and
						(IsObject (CueObj client?))
						((CueObj client?) approachX?)
					)
					(ego
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							((CueObj client?) approachY?)
							CueObj
					)
				else
					(ego setMotion: PolyPath evX evY)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoFlyOut of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== register 1)
			(if (ego cel?) (ego cel: 0))
			(if (OneOf (soundFx prevSignal?) 10 20 30)
				(if (== (soundFx prevSignal?) 30) (= cycles 15))
				(soundFx prevSignal: 0)
				(ego cel: (+ (ego cel?) 1))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 315 self)
			)
			(1
				(ego
					setPri: 14
					view: 25
					setLoop: 1
					cel: 0
					x: 292
					y: 125
					setCycle: EndLoop self
				)
			)
			(2
				(ego setPri: 10 setLoop: 2 cel: 0 setCycle: EndLoop self)
				(SolvePuzzle 10 f35FlyOut)
			)
			(3
				(ego view: 55 setLoop: 0 cel: 0 setPri: 2)
				(ship dispose:)
				(= register 1)
				(soundFx number: 436 loop: 0 play:)
			)
			(4
				(if (< (Random 0 99) 50)
					(self setScript: backup self)
				else
					(= ticks 18)
				)
			)
			(5
				(= register 0)
				(soundFx fade:)
				(ego
					setCycle: Forward
					setStep: 5 5
					moveSpeed: 0
					setPri: 9
					cycleSpeed: 5
					setMotion: MoveTo 391 110 self
				)
			)
			(6 (curRoom newRoom: 36))
		)
	)
)

(instance backup of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setCycle: Forward
					setStep: 8 8
					moveSpeed: 0
					cycleSpeed: 5
					setPri: 9
					setMotion: MoveTo 120 155 self
				)
			)
			(1
				(ego cycleSpeed: 0)
				(explosion init: setCycle: EndLoop self)
			)
			(2
				(explosion dispose:)
				(Print 35 1)
				(self dispose:)
			)
		)
	)
)

(instance blowWhistle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (++ local251) register)
					(= register (Random 3 7))
					(= local251 0)
				else
					(= state -1)
				)
				(whistle setLoop: 2 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(1
				(whistle setCycle: EndLoop)
				(if (not local253)
					(steamSound loop: 0 play: (if local256 40 127 else 0))
					(steam show: cel: 0 setCycle: Oscillate 1 self)
					(steam2 show: cel: 0 setCycle: Oscillate 1)
				else
					(= cycles 30)
				)
			)
			(2
				(= state -1)
				(steam hide:)
				(steam2 hide:)
				(steamSound stop:)
				(whistle setCycle: BegLoop self)
			)
		)
	)
)

(instance ledge of Feature
	(properties
		x 113
		y 111
		z 60
		nsTop 45
		nsLeft 49
		nsBottom 58
		nsRight 178
		description {ledge}
		sightAngle 45
		onMeCheck FARCHECK
		lookStr {There's an upper level, with a balcony and a dark doorway. You can't see much beyond that.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 35 2)
			)
			(verbSmell
				(Print 35 3)
			)
			(verbTaste
				(Print 35 4)
			)
			(verbTalk
				(Print 35 5)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance computer of Feature
	(properties
		x 160
		y 100
		heading 270
		nsBottom 180
		nsRight 320
		description {computer console}
		sightAngle 45
		onMeCheck NEARCHECK
		lookStr {A computer console, a bit archaic by the standards of the Arcada, occupies the upper right corner of the room. 
		You notice that it has a cartridge reader, much like the one you remember seeing on the Arcada's computer (except that this one hasn't been blown to smithereens).}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (== ((Inventory at: iCartridge) owner?) 35)
					(if (< (GetDistance x y (ego x?) (ego y?) 60) 30)
						(ego setScript: viewCartridge)
					else
						(ego setScript: viewCartridge 0 1)
					)
				else
					(Print 35 6)
				)
			)
			(verbUse
				(switch theItem
					(iCartridge
						(if (== egoSitting TRUE)
							(curRoom setScript: sittingInsertCart)
						else
							(curRoom setScript: standingInsertCart)
						)
					)
					(else
						(NoResponse)
					)
				)
			)
			(verbSmell
				(Print 35 7)
			)
			(verbTaste
				(Print 35 8)
			)
			(verbTalk
				(Print 35 9)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 162
		y 111
		nsTop 95
		nsLeft 140
		nsBottom 125
		nsRight 165
		description {chair}
		sightAngle 45
		onMeCheck $0010
		lookStr {This must be the chair you sit in to operate the computer.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if (== egoSitting FALSE)
					(curRoom setScript: sitDown)
				else
					(curRoom setScript: standUp)
				)
			)
			(verbSmell
				(Print 35 10)
			)
			(verbTaste
				(Print 35 11)
			)
			(verbTalk
				(Print 35 12)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance portal of Feature
	(properties
		description {portal}
		onMeCheck $2000
		lookStr {There seems to be a portal through which the skimmer may exit and enter. It must lead to the surface.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 35 13))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance computerPipes of Feature
	(properties
		description {computer pipes}
		onMeCheck cLRED
		lookStr {From the looks of these pipes, even the computer is powered by steam!}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(Print 35 14)
			)
			(verbTalk
				(Print 35 15)
			)
			(verbSmell
				(Print 35 16)
			)
			(verbTaste
				(Print 35 17)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance windows of Feature
	(properties
		description {skylights}
		onMeCheck $0800
		lookStr {What appear to be skylights made from gigantic oysters filter the intense Keronian sunlight down to these lower levels.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(Print 35 18)
			)
			(verbTalk
				(Print 35 19)
			)
			(verbSmell
				(Print 35 20)
			)
			(verbTaste
				(Print 35 21)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skimmerStand of Feature
	(properties
		y 124
		description {skimmer platform}
		onMeCheck $0400
		lookStr {The platform under the skimmer looks like it was put together from old steam generator parts.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(switch theVerb
			(verbDo
				(Print 35 22)
			)
			(verbTalk
				(Print 35 23)
			)
			(verbSmell
				(Print 35 24)
			)
			(verbTaste
				(Print 35 25)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance machinery of Feature
	(properties
		description {machinery}
		onMeCheck $0200
		lookStr {The steam generator is a fascinating device in constant motion. It looks like it was assembled following a field trip to the junkyard, but everything appears to be in working order.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(Print 35 26)
			)
			(verbTalk
				(Print 35 27)
			)
			(verbTaste
				(Print 35 28)
			)
			(verbSmell
				(Print 35 29)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance steamBlowers of Feature
	(properties
		description {steam vents}
		onMeCheck $0100
		lookStr {Every few seconds, steam escapes from the vents to let off excess pressure.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(Print 35 30)
			)
			(verbTalk
				(Print 35 31)
			)
			(verbSmell
				(Print 35 32)
			)
			(verbTaste
				(Print 35 33)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bigPipes of Feature
	(properties
		description {big pipes}
		onMeCheck $0080
		lookStr {Large metal cylinders form supports for the upper level.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(Print 35 34)
			)
			(verbTalk
				(Print 35 35)
			)
			(verbSmell
				(Print 35 36)
			)
			(verbTaste
				(Print 35 37)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance smallPipes of Feature
	(properties
		description {small pipes}
		onMeCheck $0040
		lookStr {These pipes are probably part of a larger network that carries steam to power various Keronian devices.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(Print 35 38)
			)
			(verbTalk
				(Print 35 39)
			)
			(verbSmell
				(Print 35 40)
			)
			(verbTaste
				(Print 35 41)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dialPipes of Feature
	(properties
		x 210
		y 84
		description {small pipes with dials}
		onMeCheck $0020
		approachX 203
		approachY 134
		lookStr {These gauges appear to monitor the pressure in the pipes. You think about the thousands of pounds of boiling hot steam in the system, and hope the gauges work.}
	)
	
	(method (init)
		(self approachVerbs: verbDo verbSmell verbTaste)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 35 42)
			)
			(verbTalk
				(Print 35 43)
			)
			(verbSmell
				(Print 35 44)
			)
			(verbTaste
				(Print 35 45)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance floor of Feature
	(properties
		description {floor}
		onMeCheck ISNOTHIDDEN
		lookStr {The floor in this place is made of polished slate. It is impeccably clean. With all this steam, the Keronians undoubtedly use it to steam clean just about everything.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(verbDo
				(Print 35 46)
			)
			(verbTalk
				(Print 35 47)
			)
			(verbSmell
				(Print 35 48)
			)
			(verbTaste
				(Print 35 49)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance viewCart of View
	(properties
		x 186
		y 121
		description {data cartridge}
		view 25
		loop 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 35 50)
			)
			(verbDo
				(if (== egoSitting TRUE)
					(curRoom setScript: sittingRetrieveCart)
				else
					(curRoom setScript: standingRetrieveCart)
				)
			)
			(verbSmell
				(Print 35 51)
			)
			(verbTaste
				(Print 35 52)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (!= (ego x?) 183) (!= (ego y?) 131))
					(ego setMotion: PolyPath 183 131 self)
				else
					(= state 0)
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: MoveTo 183 131 self)
			)
			(2
				(ego view: 25 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego loop: 4 cel: 0 x: 173 y: 115)
				(= egoSitting TRUE)
				(HandsOn)
				(= cycles 1)
			)
			(4 (self dispose:))
		)
	)
)

(instance standUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 183 y: 131 setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(1
				(NormalEgo 2 1 61)
				(ego observeControl: cWHITE)
				(= egoSitting FALSE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sittingInsertCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 173 y: 114 setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(1
				(soundFx number: 303 loop: 1 play:)
				(Print 35 53)
				(viewCart init:)
				(ego cel: 0)
				(self setScript: viewCartridge self)
				(ego put: iCartridge 35)
				(screen loop: 4 setCycle: Forward)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance standingInsertCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (!= (ego x?) 164) (!= (ego y?) 131))
					(ego setMotion: PolyPath 164 131 self)
				else
					(= cycles 3)
				)
			)
			(1
				(ego setMotion: MoveTo 164 131 self)
			)
			(2
				(ego view: 25 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				(soundFx number: 303 loop: 1 play:)
				(viewCart init:)
				(self setScript: viewCartridge self)
				(ego put: iCartridge 35)
				(screen loop: 4 setCycle: Forward)
			)
			(4
				(NormalEgo 0 1 61)
				(ego observeControl: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance standingRetrieveCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (!= (ego x?) 164) (!= (ego y?) 131))
					(ego setMotion: PolyPath 164 131 self)
				else
					(= cycles 3)
				)
			)
			(1
				(ego setMotion: MoveTo 164 131 self)
			)
			(2
				(ego view: 25 setLoop: 0 cel: 9 setCycle: CycleTo 3 -1 self)
			)
			(3
				(soundFx number: 303 loop: 1 play:)
				(ego setCycle: BegLoop self)
				(viewCart dispose:)
			)
			(4
				(screen loop: 6 cel: 0)
				(NormalEgo 0 1 61)
				(ego observeControl: cWHITE)
				(HandsOn)
				(ego get: iCartridge)
				(self dispose:)
			)
		)
	)
)

(instance sittingRetrieveCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 8
					x: 173
					y: 114
					setLoop: 4
					setCel: 8
					setCycle: CycleTo 6 -1 self
				)
				(viewCart dispose:)
			)
			(1
				(screen loop: 6 cel: 0)
				(ego get: 0)
				(= cycles 1)
			)
			(2 (ego setCycle: CycleTo 3 -1 self))
			(3
				(soundFx number: 303 loop: 1 play:)
				(viewCart dispose:)
				(ego setCycle: BegLoop self)
			)
			(4
				(ego setPri: -1)
				(self setScript: standUp self)
			)
			(5 (self dispose:))
		)
	)
)

(instance viewCartridge of DScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(ego setMotion: PolyPath 164 131 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if register
					(= register 0)
					(ego setHeading: 90 self)
				else
					(= ticks 2)
				)
			)
			(2
				(theMusic fade:)
				(steamSound stop:)
				(engineSound stop:)
				(worker signal: (| (worker signal?) $0100))
				(= cycles 3)
			)
			(3
				(= local253 1)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 2 FADEOUT)
				(= seconds 2)
			)
			(4
				(self
					save1:
						(Display 35 54
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 1)
			)
			(5
				(self restore:)
				(= cycles 12)
			)
			(6
				(theMusic2 number: 413 loop: -1 play:)
				(self
					save1:
						(Display 35 55
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 10)
			)
			(7
				(self restore:)
				(= cycles 6)
			)
			(8
				(self
					save1:
						(Display 35 56
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 16)
			)
			(9
				(self restore:)
				(= cycles 6)
			)
			(10
				(self
					save1:
						(Display 35 57
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 10)
			)
			(11
				(self restore:)
				(= cycles 6)
			)
			(12
				(self
					save1:
						(Display 35 58
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 12)
			)
			(13
				(self restore:)
				(= cycles 6)
			)
			(14
				(self
					save1:
						(Display 35 59
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 12)
			)
			(15
				(self restore:)
				(= cycles 6)
			)
			(16
				(self
					save1:
						(Display 35 60
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 12)
			)
			(17
				(self restore:)
				(= cycles 6)
			)
			(18
				(self
					save1:
						(Display 35 61
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 12)
			)
			(19
				(self restore:)
				(= cycles 6)
			)
			(20
				(self
					save1:
						(Display 35 62
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 10)
			)
			(21
				(self restore:)
				(= cycles 6)
			)
			(22
				(SolvePuzzle 5 fLearnSelfDestructCode)
				(Format @str 35 63 selfDestructCode)
				(self
					save1:
						(Display @str
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 20)
			)
			(23
				(self restore:)
				(= cycles 6)
			)
			(24
				(self
					save1:
						(Display 35 64
							p_mode teJustCenter
							p_width 165
							p_at 78 40
							p_color colLYellow
							p_save
						)
				)
				(= seconds 16)
			)
			(25
				(self restore:)
				(= cycles 9)
			)
			(26
				(theMusic2 fade: loop: 0)
				(theMusic play: 0 fade: 127 25 10 0)
				(cast eachElementDo: #show)
				((ego _head?) hide:)
				(curRoom drawPic: 35 FADEOUT)
				(steam hide:)
				(steam2 hide:)
				(= local253 0)
				(worker signal: (& (worker signal?) $feff))
				(screen loop: 6 cel: 0)
				(self dispose:)
			)
		)
	)
	
	(method (restore)
		(super restore: &rest)
		(= local250 0)
	)
)

(instance ship of Actor
	(properties
		x 292
		y 125
		description {blue skimmer}
		sightAngle 45
		approachX 301
		approachY 170
		lookStr {As described, it is a sand skimmer and it looks like it has skimmed quite a bit of sand in its time. On it is a panel which has a small readout, currently dark, and a keyhole with a key in it.}
		view 135
		loop 3
		priority 10
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 6
		moveSpeed 6
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: egoFlyOut)
			)
			(verbSmell
				(Print 35 65)
			)
			(verbTaste
				(Print 35 66)
			)
			(verbTalk
				(Print 35 67)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ladder of View
	(properties
		x 292
		y 126
		z 1
		description {ladder}
		sightAngle 45
		approachX 301
		approachY 170
		lookStr {The ladder appears to have been put here to make it easier to board the skimmer.}
		view 135
		loop 3
		cel 1
		priority 11
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(ship doVerb: verbDo &rest)
			)
			(verbSmell
				(Print 35 68)
			)
			(verbTaste
				(Print 35 69)
			)
			(verbTalk
				(Print 35 70)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance explosion of Prop
	(properties
		x 92
		y 173
		view 328
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
)

(instance lightBulb1 of Prop
	(properties
		x 135
		y 180
		z 21
		description {flickering lightbulb}
		sightAngle 45
		lookStr {These glass tubes are really quite intriguing - you've never seen anything remotely like them. Must be some radical new technology.}
		view 135
		cel 1
		priority 15
		signal fixPriOn
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 35 71)
			)
			(verbSmell
				(Print 35 72)
			)
			(verbTaste
				(Print 35 73)
			)
			(verbTalk
				(Print 35 74)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lightBulb2 of Prop
	(properties
		x 113
		y 180
		z 18
		description {flickering lightbulb}
		sightAngle 45
		view 135
		cel 1
		priority 15
		signal fixPriOn
		cycleSpeed 2
	)
	
	(method (doVerb)
		(lightBulb1 doVerb: &rest)
	)
)

(instance lightBulb3 of Prop
	(properties
		x 92
		y 180
		z 12
		description {flickering lightbulb}
		sightAngle 45
		view 135
		cel 1
		priority 14
		signal fixPriOn
		cycleSpeed 2
	)
	
	(method (doVerb)
		(lightBulb1 doVerb: &rest)
	)
)

(instance worker of Actor
	(properties
		x 7
		y 167
		description {Keronian worker}
		lookStr {These people of Kerona sure are an odd-looking lot. You can't pin it down, but you are sure something is different about them.}
		view 430
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 5
		moveSpeed 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 35 75)
			)
			(verbTalk
				(Print 35 75)
			)
			(verbSmell
				(Print 35 76)
			)
			(verbTaste
				(Print 35 77)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 35 78)
					)
					(iCartridge
						(Print 35 79)
					)
					(iWidget
						(Print 35 80)
					)
					(iGadget
						(Print 35 81)
					)
					(iDehydratedWater
						(Print 35 82)
					)
					(iKnife
						(Print 35 83)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance hoverThing of Prop
	(properties
		x 7
		y 167
		description {hover platform.}
		lookStr {The Keronian is hovering a meter or so off the floor on what appears to be a Kamen Kruiser, something like the one you used to buzz the family dog with as a young child.}
		view 430
		loop 4
		cel 1
		priority 3
		signal (| ignrAct fixPriOn)
		cycleSpeed 5
	)
	
	(method (doit)
		(self
			x: (- (theWorker x?) 0)
			y: (+ (theWorker y?) 21)
		)
		(super doit: &rest)
		(if
			(and
				(< 0 x)
				(< x 320)
				(< 0 y)
				(< y 200)
				(not local257)
			)
			(= local257 1)
			(hoverSound number: 462 loop: 1 play: hold: 1)
		else
			(= local257 0)
			(hoverSound hold: 0)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 35 84)
			)
			(verbTaste
				(Print 35 85)
			)
			(verbSmell
				(Print 35 86)
			)
			(verbTalk
				(Print 35 87)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance shaft of Prop
	(properties
		x 51
		y 180
		z 35
		description {shaft}
		sightAngle 45
		lookStr {The shaft must be part of the steam generator.}
		view 135
		loop 1
		cel 4
		priority 15
		signal fixPriOn
		cycleSpeed 4
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (== cel 5) (!= theCel cel) (not local253))
			(engineSound loop: 0 play: (if local256 40 else 127))
		)
		(= theCel cel)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 35 88)
			)
			(verbSmell
				(Print 35 89)
			)
			(verbTaste
				(Print 35 90)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance screen of Prop
	(properties
		x 178
		y 104
		description {computer screen}
		sightAngle 45
		view 135
		loop 6
		signal stopUpdOn
		cycleSpeed 14
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (== loop 6)
					(Print 35 91)
					else (Print 35 92)
				)
			)
			(verbDo
				(if (== ((Inventory at: iCartridge) owner?) 35)
					(if (< (GetDistance x y (ego x?) (ego y?) 60) 30)
						(ego setScript: viewCartridge)
					else
						(ego setScript: viewCartridge 0 1)
					)
				else
					(Print 35 93)
				)
			)
			(verbUse
				(switch theItem
					(iCartridge
						(if (== egoSitting TRUE)
							(curRoom setScript: sittingInsertCart)
						else
							(curRoom setScript: standingInsertCart)
						)
					)
					(else
						(NoResponse)
					)
				)
			)
			(else 
				(computer doVerb: theVerb &rest)
			)
		)
	)
)

(instance whistle of Prop
	(properties
		x 22
		y 180
		z 89
		description {steam vent}
		sightAngle 45
		view 135
		loop 2
		priority 6
		signal (| ignrAct fixPriOn)
		cycleSpeed 17
	)
	
	(method (doVerb)
		(steamBlowers doVerb: &rest)
	)
)

(instance leader of Actor
	(properties
		x 7
		y 152
		description {Keronian leader.}
		lookStr {The old Keronian guy looks friendly enough, but his color makes you wonder if he's getting enough oxygen.}
		view 427
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 5
		moveSpeed 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 35 94)
			)
			(verbTalk
				(Print 35 95)
			)
			(verbSmell
				(Print 35 96)
			)
			(verbTaste
				(Print 35 97)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 35 98)
					)
					(iCartridge
						(Print 35 99)
					)
					(iWidget
						(Print 35 100)
					)
					(iGadget
						(Print 35 101)
					)
					(iDehydratedWater
						(Print 35 102)
					)
					(iKnife
						(Print 35 103)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance steam of Prop
	(properties
		x 28
		y 55
		description {steam}
		sightAngle 45
		lookStr {Some steam is escaping from the generator. That explains why this place is so humid.}
		view 135
		loop 5
		cel 2
		priority 5
		signal (| fixPriOn stopUpdOn)
		cycleSpeed 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(NoResponse)
			)
			(verbTaste
				(Print 35 104)
			)
			(verbSmell
				(Print 35 105)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance steam2 of Prop
	(properties
		x 40
		y 69
		description {steam escaping from the pipes.}
		sightAngle 45
		view 135
		loop 5
		cel 1
		priority 5
		signal (| fixPriOn stopUpdOn)
		cycleSpeed 5
	)
	
	(method (doVerb)
		(steam doVerb: &rest)
	)
)

(instance hoverSound of Sound
	(properties
		number 462
	)
)

(instance engineSound of Sound
	(properties
		number 461
	)
)

(instance steamSound of Sound
	(properties
		number 460
	)
)

(instance leaderTalker of Talker
	(properties
		x 40
		y 9
		nsTop 4
		nsLeft 207
		view 505
		loop 1
	)
)

(instance leaderBust of View
	(properties
		view 505
		loop 1
		cel 1
	)
)

(instance leaderMouth of Prop
	(properties
		nsTop 58
		nsLeft 17
		view 505
		loop 5
		cycleSpeed 15
	)
)

(instance leaderEyes of Prop
	(properties
		nsTop 28
		nsLeft 15
		view 505
		loop 3
		cycleSpeed 35
	)
)

(instance leaderEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theWorker leader)
				(hoverThing init: setCycle: Forward)
				(leader
					show:
					cycleSpeed: 8
					moveSpeed: 0
					setMotion: MoveTo 127 122 self
				)
			)
			(1 (= cycles 20))
			(2 (leader setCycle: EndLoop self))
			(3
				(hoverSound fade: 40 25 10 0)
				(theMusic setVol: 40)
				(= local256 1)
				(theMusic2 number: 458 loop: -1 flags: 1 play:)
				(leaderTalker
					posn: 100 120
					init:
						leaderBust leaderMouth leaderEyes
						{"Please, don't be alarmed. We intend no harm. We are a peaceful race. We are cautious, however. Others don't share our way of life. Welcome to Kerona."}
						0 0 0
						self
				)
			)
			(4
				(leaderTalker
					say:
						{"You are standing in the power generation facility of our underground settlement. All power here is produced by steam. That is unimportant to you, however. We have promised you transportation."}
						0 0 0
						self
				)
			)
			(5
				(leaderTalker
					say:
						{"It is a Skimmer. It hovers approximately 1/2 meter above the traveling surface. This is very important because of Grell."}
						0 0 0
						self
				)
			)
			(6
				(leaderTalker
					say:
						{"Grell and his like dwell in caves below the sand. If you stand on the surface too long, you chance becoming a rare moist meal for him."}
						0 0 0
						self
				)
			)
			(7
				(leaderTalker
					say:
						{"The skimmer is programmed to take you to a settlement on the other side of Kerona called Ulence Flats. You can make further travel arrangements there."}
						0 0 0
						self
				)
			)
			(8
				(leaderTalker
					say:
						{"I am sorry this is all we can offer. I hope your trip is a safe one. Board the skimmer when you are ready to depart. Good luck, strange one."}
						0 0 1
						self
				)
			)
			(9
				(= local256 0)
				(hoverSound fade: 127 25 10 0)
				(theMusic fade: 127 25 10 0)
				(theMusic2 flags: 0 fade:)
				(leader xStep: 5)
				(= cycles 2)
			)
			(10
				(leader setCycle: BegLoop self)
				(HandsOn)
			)
			(11
				(leader setMotion: MoveTo 0 122 self)
			)
			(12
				(leader dispose:)
				(hoverThing dispose:)
				(self dispose:)
			)
		)
	)
)
