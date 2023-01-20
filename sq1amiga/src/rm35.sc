;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include sci.sh)
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
		lookStr {This place is most interesting, indeed. A mixture of both old and new technologies makes you wonder about its origin. Or at least about its tech support. On one side of the chamber is a primitive steam generator. Its pistons, pounding rhythmically, turn steam into useful energy. On the other side is what appears to be a computer console.}
		picture 35
		style $8009
	)
	
	(method (init)
		(LoadMany 128 61 430 135 427 25 328)
		(Load rsSCRIPT 964)
		(LoadMany 132 436 462 461 460 413)
		(HandsOff)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						189
						0
						0
						319
						0
						319
						170
						262
						174
						253
						170
						228
						170
						214
						166
						213
						166
						190
						155
						189
						148
						208
						135
						198
						131
						178
						138
						152
						138
						139
						130
						140
						121
						108
						121
						103
						148
						186
						189
					yourself:
				)
		)
		(super init:)
		(ship init: approachVerbs: 3)
		(ladder init: approachVerbs: 3)
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
		(lightBulb3 init: setCycle: Fwd cycleSpeed: 5)
		(lightBulb2 init: setCycle: Fwd cycleSpeed: 8)
		(lightBulb1 init: setCycle: Fwd cycleSpeed: 11)
		(shaft init: setCycle: Rev cycleSpeed: 8)
		(screen init: stopUpd:)
		(whistle init: setScript: blowWhistle 0 (Random 3 7))
		(ego init: view: 1 posn: 317 249 ignoreControl: -32768)
		(= egoSitting 0)
		(HandsOff)
		(self setScript: egoEnterRoom)
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
			((& (ego onControl:) $4000) (if (!= (ego priority?) 13) (ego setPri: 13)))
			((== egoSitting 1) 0)
			((& (ego signal?) $0010) (ego setPri: -1))
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
				(ego setLoop: -1 observeControl: -32768)
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
			(0 (= seconds (Random 3 6)))
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
				(hoverThing init: setLoop: 5 setCycle: Rev)
			)
			(3 (worker setCycle: End self))
			(4
				(worker setLoop: 9 cel: 0 setCycle: Osc 3 self)
			)
			(5
				(worker setLoop: 8 setCycle: Beg self)
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
				(ego x: 183 y: 131 setLoop: 5 cel: 0 setCycle: End self)
			)
			(1
				(NormalEgo 2 1 61)
				(ego observeControl: -32768)
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
					setCycle: End self
				)
			)
			(2
				(ego setPri: 10 setLoop: 2 cel: 0 setCycle: End self)
				(SolvePuzzle 10 151)
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
					setCycle: Fwd
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
					setCycle: Fwd
					setStep: 8 8
					moveSpeed: 0
					cycleSpeed: 5
					setPri: 9
					setMotion: MoveTo 120 155 self
				)
			)
			(1
				(ego cycleSpeed: 0)
				(explosion init: setCycle: End self)
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
				(whistle setLoop: 2 cel: 0 setCycle: CT 1 1 self)
			)
			(1
				(whistle setCycle: End)
				(if (not local253)
					(steamSound loop: 0 play: (if local256 40 127 else 0))
					(steam init: cel: 0 setCycle: Osc 1 self)
					(steam2 init: cel: 0 setCycle: Osc 1)
				else
					(= cycles 30)
				)
			)
			(2
				(= state -1)
				(steam dispose:)
				(steam2 dispose:)
				(steamSound stop:)
				(whistle setCycle: Beg self)
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
		onMeCheck $0004
		lookStr {There's an upper level, with a balcony and a dark doorway. You can't see much beyond that.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 35 2))
			(12 (Print 35 3))
			(11 (Print 35 4))
			(5 (Print 35 5))
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
		onMeCheck $0002
		lookStr {A computer console, a bit archaic by the standards of the Arcada, occupies the upper right corner of the room. You notice that it has a cartridge reader, much like the one you remember seeing on the Arcada's computer (except that this one hasn't been blown to smithereens).}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if (== ((Inv at: 0) owner?) 35)
					(if (< (GetDistance x y (ego x?) (ego y?) 60) 30)
						(ego setScript: viewCartridge2)
					else
						(ego setScript: viewCartridge2 0 1)
					)
				else
					(Print 35 6)
				)
			)
			(4
				(switch theItem
					(0
						(if (== egoSitting 1)
							(curRoom setScript: sittingInsertCart)
						else
							(curRoom setScript: standingInsertCart)
						)
					)
					(else  (NoResponse))
				)
			)
			(12 (Print 35 7))
			(11 (Print 35 8))
			(5 (Print 35 9))
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
			(3
				(if (== egoSitting 0)
					(curRoom setScript: sitDown)
				else
					(curRoom setScript: standUp)
				)
			)
			(12 (Print 35 10))
			(11 (Print 35 11))
			(5 (Print 35 12))
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
		onMeCheck $1000
		lookStr {From the looks of these pipes, even the computer is powered by steam!}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 35 14))
			(5 (Print 35 15))
			(12 (Print 35 16))
			(11 (Print 35 17))
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
			(3 (Print 35 18))
			(5 (Print 35 19))
			(12 (Print 35 20))
			(11 (Print 35 21))
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
			(3 (Print 35 22))
			(5 (Print 35 23))
			(12 (Print 35 24))
			(11 (Print 35 25))
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
			(3 (Print 35 26))
			(5 (Print 35 27))
			(12 (Print 35 28))
			(11 (Print 35 29))
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
			(3 (Print 35 30))
			(5 (Print 35 31))
			(12 (Print 35 32))
			(11 (Print 35 33))
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
			(3 (Print 35 34))
			(5 (Print 35 35))
			(12 (Print 35 36))
			(11 (Print 35 37))
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
			(3 (Print 35 38))
			(5 (Print 35 39))
			(12 (Print 35 40))
			(11 (Print 35 41))
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
		(self approachVerbs: 3 12 11)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 35 42))
			(5 (Print 35 43))
			(12 (Print 35 44))
			(11 (Print 35 45))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance floor of Feature
	(properties
		description {floor}
		onMeCheck $0008
		lookStr {The floor in this place is made of polished slate. It is impeccably clean. With all this steam, the Keronians undoubtedly use it to steam clean just about everything.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 35 46))
			(5 (Print 35 47))
			(12 (Print 35 48))
			(11 (Print 35 49))
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
			(2 (Print 35 50))
			(3
				(if (== egoSitting 1)
					(curRoom setScript: sittingRetrieveCart)
				else
					(curRoom setScript: standingRetrieveCart)
				)
			)
			(12 (Print 35 51))
			(11 (Print 35 52))
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
				(ego view: 25 setLoop: 3 cel: 0 setCycle: End self)
			)
			(3
				(ego loop: 4 cel: 0 x: 173 y: 115)
				(= egoSitting 1)
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
				(ego x: 183 y: 131 setLoop: 5 cel: 0 setCycle: End self)
			)
			(1
				(NormalEgo 2 1 61)
				(ego observeControl: -32768)
				(= egoSitting 0)
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
				(ego x: 173 y: 114 setLoop: 4 cel: 0 setCycle: End self)
			)
			(1
				(soundFx number: 303 loop: 1 play:)
				(Print 35 53)
				(viewCart init:)
				(ego cel: 0)
				(self setScript: viewCartridge self)
				(ego put: 0 35)
				(screen loop: 4 setCycle: Fwd)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance viewCartridge2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: viewCartridge self register)
			)
			(1 (HandsOn) (self dispose:))
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
				(ego view: 25 setLoop: 0 cel: 0 setCycle: End self)
			)
			(3
				(soundFx number: 303 loop: 1 play:)
				(viewCart init:)
				(self setScript: viewCartridge self)
				(ego put: 0 35)
				(screen loop: 4 setCycle: Fwd)
			)
			(4
				(NormalEgo 0 1 61)
				(ego observeControl: -32768)
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
				(ego view: 25 setLoop: 0 cel: 9 setCycle: CT 3 -1 self)
			)
			(3
				(soundFx number: 303 loop: 1 play:)
				(ego setCycle: Beg self)
				(viewCart dispose:)
			)
			(4
				(screen loop: 6 cel: 0)
				(NormalEgo 0 1 61)
				(ego observeControl: -32768)
				(HandsOn)
				(ego get: 0)
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
					setCycle: CT 6 -1 self
				)
				(viewCart dispose:)
			)
			(1
				(screen loop: 6 cel: 0)
				(ego get: 0)
				(= cycles 1)
			)
			(2 (ego setCycle: CT 3 -1 self))
			(3
				(soundFx number: 303 loop: 1 play:)
				(viewCart dispose:)
				(ego setCycle: Beg self)
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
				(curRoom drawPic: 2 10)
				(= seconds 2)
			)
			(4
				(self
					save1:
						(Display
							35
							54
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							55
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							56
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							57
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							58
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							59
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							60
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							61
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							62
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
						)
				)
				(= seconds 10)
			)
			(21
				(self restore:)
				(= cycles 6)
			)
			(22
				(SolvePuzzle 5 152)
				(Format @str 35 63 selfDestructCode)
				(self
					save1:
						(Display
							@str
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
						(Display
							35
							64
							dsALIGN
							1
							dsWIDTH
							165
							dsCOORD
							78
							40
							dsCOLOR
							colLYellow
							dsSAVEPIXELS
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
				(curRoom drawPic: 35 10)
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
		signal $4011
		cycleSpeed 6
		moveSpeed 6
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(curRoom setScript: egoFlyOut)
			)
			(12 (Print 35 65))
			(11 (Print 35 66))
			(5 (Print 35 67))
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
		signal $4011
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (ship doVerb: 3 &rest))
			(12 (Print 35 68))
			(11 (Print 35 69))
			(5 (Print 35 70))
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
		signal $4010
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
		signal $0010
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 35 71))
			(12 (Print 35 72))
			(11 (Print 35 73))
			(5 (Print 35 74))
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
		signal $0010
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
		signal $0010
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
		signal $4810
		cycleSpeed 5
		moveSpeed 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 35 75))
			(5 (Print 35 75))
			(12 (Print 35 76))
			(11 (Print 35 77))
			(4
				(switch theItem
					(10 (Print 35 78))
					(0 (Print 35 79))
					(15 (Print 35 80))
					(2 (Print 35 81))
					(5 (Print 35 82))
					(4 (Print 35 83))
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
		signal $4010
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
			(3 (Print 35 84))
			(11 (Print 35 85))
			(12 (Print 35 86))
			(5 (Print 35 87))
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
		signal $0010
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
			(3 (Print 35 88))
			(11 (Print 35 89))
			(12 (Print 35 90))
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
		signal $0001
		cycleSpeed 14
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(if (== loop 6) (Print 35 91) else (Print 35 92))
			)
			(3
				(if (== ((Inv at: 0) owner?) 35)
					(if (< (GetDistance x y (ego x?) (ego y?) 60) 30)
						(ego setScript: viewCartridge)
					else
						(ego setScript: viewCartridge 0 1)
					)
				else
					(Print 35 93)
				)
			)
			(4
				(switch theItem
					(0
						(if (== egoSitting 1)
							(curRoom setScript: sittingInsertCart)
						else
							(curRoom setScript: standingInsertCart)
						)
					)
					(else  (NoResponse))
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
		signal $4010
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
		signal $4810
		cycleSpeed 5
		moveSpeed 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 35 94))
			(5 (Print 35 95))
			(12 (Print 35 96))
			(11 (Print 35 97))
			(4
				(switch theItem
					(10 (Print 35 98))
					(0 (Print 35 99))
					(15 (Print 35 100))
					(2 (Print 35 101))
					(5 (Print 35 102))
					(4 (Print 35 103))
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
		signal $0011
		cycleSpeed 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (NoResponse))
			(11 (Print 35 104))
			(12 (Print 35 105))
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
		signal $0011
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
				(hoverThing init: setCycle: Fwd)
				(leader
					init:
					cycleSpeed: 8
					moveSpeed: 0
					setMotion: MoveTo 127 122 self
				)
			)
			(1 (= cycles 20))
			(2 (leader setCycle: End self))
			(3
				(hoverSound fade: 40 25 10 0)
				(theMusic setVol: 40)
				(= local256 1)
				(theMusic2 number: 458 loop: -1 flags: 1 play:)
				(leaderTalker
					posn: 100 120
					init:
						leaderBust
						leaderMouth
						leaderEyes
						{"Please, don't be alarmed. We intend no harm. We are a peaceful race. We are cautious, however. Others don't share our way of life. Welcome to Kerona."}
						0
						0
						0
						self
				)
			)
			(4
				(leaderTalker
					say:
						{"You are standing in the power generation facility of our underground settlement. All power here is produced by steam. That is unimportant to you, however. We have promised you transportation."}
						0
						0
						0
						self
				)
			)
			(5
				(leaderTalker
					say:
						{"It is a Skimmer. It hovers approximately 1/2 meter above the traveling surface. This is very important because of Grell."}
						0
						0
						0
						self
				)
			)
			(6
				(leaderTalker
					say:
						{"Grell and his like dwell in caves below the sand. If you stand on the surface too long, you chance becoming a rare moist meal for him."}
						0
						0
						0
						self
				)
			)
			(7
				(leaderTalker
					say:
						{"The skimmer is programmed to take you to a settlement on the other side of Kerona called Ulence Flats. You can make further travel arrangements there."}
						0
						0
						0
						self
				)
			)
			(8
				(leaderTalker
					say:
						{"I am sorry this is all we can offer. I hope your trip is a safe one. Board the skimmer when you are ready to depart. Good luck, strange one."}
						0
						0
						1
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
				(leader setCycle: Beg self)
				(HandsOn)
			)
			(11
				(leader setMotion: MoveTo 0 122 self)
			)
			(12
				(leader dispose:)
				(hoverThing dispose:)
				(worker init: hide: setScript: workerDoYourThing)
				(self dispose:)
			)
		)
	)
)
