;;; Sierra Script 1.0 - (do not remove this comment)
(script# 425)
(include sci.sh)
(use Main)
(use rLab)
(use n402)
(use Kq6Procs)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	rm425 0
)

(local
	local0
)
(instance rm425 of LabRoom
	(properties
		style $000a
		east 400
		west 411
	)
	
	(method (init)
		(proc402_1)
		(super init: &rest)
		(westDoor addToPic:)
		(westWing addToPic:)
		((ScriptID 30 3) init:)
		((ScriptID 30 0) initCrypt: 1)
		((ScriptID 30 6) addToPic:)
		((ScriptID 30 10) addToPic:)
		((ScriptID 30 8) addToPic:)
		(curRoom setScript: (ScriptID 30 1) self)
	)
	
	(method (dispose)
		((ScriptID 30 0) geniePresent: 0)
		(super dispose:)
	)
	
	(method (cue)
		(if (not (Btst 1))
			(switch ((ScriptID 30 0) timesGenieHasAppeared?)
				(0
					(genie init: setScript: genieMeeting)
					((ScriptID 30 0) geniePresent: 1)
				)
				(1
					(genieMeeting start: 12)
					(genie init: setScript: genieMeeting)
					((ScriptID 30 0) geniePresent: 1)
				)
				(2
					(genieMeeting start: 22)
					(genie init: setScript: genieMeeting)
					((ScriptID 30 0) geniePresent: 1)
				)
				(else 
					((ScriptID 30 0)
						timesGenieHasAppeared: (+ ((ScriptID 30 0) timesGenieHasAppeared?) 1)
					)
				)
			)
		)
	)
	
	(method (notify)
		(westDoor addToPic:)
		(westWing addToPic:)
		((ScriptID 30 6) addToPic:)
		((ScriptID 30 10) addToPic:)
		((ScriptID 30 8) addToPic:)
		((ScriptID 30 3) show:)
	)
)

(instance genieMeeting of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(if (< (ego x?) 130)
					(ego setMotion: PolyPath 130 (ego y?) self)
				else
					(self cue:)
				)
			)
			(2
				((ScriptID 30 0)
					timesGenieHasAppeared: (+ ((ScriptID 30 0) timesGenieHasAppeared?) 1)
				)
				(= temp1 (GetAngle (ego x?) (ego y?) 85 158))
				(ego setHeading: temp1)
				(= local0 1)
				(genie
					posn: 26 158
					init:
					setCycle: Walk
					setMotion: PolyPath 85 158 self
				)
			)
			(3 (glint init:))
			(4
				(genie view: 442 setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(5 (= seconds 2))
			(6 (genie setCycle: BegLoop self))
			(7
				(messager say: 1 0 20 0 self 400)
			)
			(8
				(genie view: 442 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(9
				(genie view: 442 setLoop: 4 cel: 0)
				(= cycles 2)
			)
			(10
				(genie
					view: 364
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath 26 164 self
				)
			)
			(11
				(theGame handsOn:)
				(= seconds 30)
			)
			(12
				(theGame handsOff:)
				(= seconds 3)
			)
			(13
				(genie posn: 26 158)
				(= cycles 2)
			)
			(14
				(if (< (ego x?) 130)
					(ego setMotion: PolyPath 130 (ego y?) self)
				else
					(self cue:)
				)
			)
			(15
				((ScriptID 30 0)
					timesGenieHasAppeared: (+ ((ScriptID 30 0) timesGenieHasAppeared?) 1)
				)
				(= temp1 (GetAngle (ego x?) (ego y?) 85 158))
				(ego setHeading: temp1)
				(= local0 1)
				(genie setCycle: Walk setMotion: PolyPath 85 158 self)
			)
			(16 (glint init:))
			(17
				(genie view: 442 setLoop: 6 cel: 0)
				(= cycles 10)
			)
			(18
				(messager say: 1 0 21 1 self 400)
			)
			(19
				(genie
					view: 442
					setLoop: 2
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(20
				(genie
					view: 364
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath 26 164 self
				)
			)
			(21
				(theGame handsOn:)
				(= seconds 30)
			)
			(22
				(theGame handsOff:)
				(= seconds 3)
			)
			(23
				(genie posn: 26 164)
				(= cycles 2)
			)
			(24
				(if (< (ego x?) 130)
					(ego setMotion: PolyPath 130 (ego y?) self)
				else
					(self cue:)
				)
			)
			(25
				((ScriptID 30 0)
					timesGenieHasAppeared: (+ ((ScriptID 30 0) timesGenieHasAppeared?) 1)
				)
				(= temp1 (GetAngle (ego x?) (ego y?) 85 158))
				(ego setHeading: temp1)
				(= local0 1)
				(genie setCycle: Walk setMotion: PolyPath 85 158 self)
			)
			(26 (glint init:))
			(27
				(genie view: 442 setLoop: 6 cel: 0)
				(= cycles 10)
			)
			(28
				(messager say: 1 0 22 1 self 400)
			)
			(29
				(genie
					view: 442
					setLoop: 5
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(30 (genie setCycle: BegLoop self))
			(31
				(genie
					view: 442
					setLoop: 2
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(32
				(genie
					view: 364
					setLoop: -1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 26 164 self
				)
			)
			(33
				(theGame handsOn:)
				(genie dispose:)
			)
		)
	)
)

(instance backOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setHeading: 270 self))
			(1
				(ego
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) 20) (ego y?) self
				)
			)
			(2
				(ego reset: 1)
				(self dispose:)
			)
		)
	)
)

(instance genie of Actor
	(properties
		modNum 400
		view 364
	)
)

(instance glint of Prop
	(properties
		view 902
		cycleSpeed 0
	)
	
	(method (init)
		(self
			posn: (+ (genie x?) 3) (- (genie y?) 44)
			setPri: 15
			setCycle: EndLoop self
		)
		(super init:)
	)
	
	(method (cue)
		(if (> cel 0)
			(self setCycle: BegLoop self)
		else
			(self dispose:)
			(genieMeeting cue:)
		)
	)
)

(instance exitSouth of View
	(properties
		x 176
		y 166
		noun 6
		modNum 400
		view 408
		priority 15
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 6 1 9 0 0 400)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance westDoor of View
	(properties
		x 63
		y 155
		modNum 400
		view 405
		cel 1
		priority 11
		signal $4010
	)
	
	(method (init)
		(self stopUpd:)
		(westWing init: stopUpd:)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local0
					(messager say: 14 1 25 1 0 400)
				else
					(messager say: 4 1 0 1 0 400)
				)
			)
			(5
				(if local0
					(messager say: 14 5 25 1 0 400)
				else
					(messager say: 4 5 0 1 0 400)
				)
			)
			(2
				(if local0
					(messager say: 14 2 25 0 0 400)
				else
					(messager say: 4 2 0 1 0 400)
				)
			)
			(else 
				(messager say: 4 0 0 1 0 400)
			)
		)
	)
)

(instance westWing of View
	(properties
		x 43
		y 168
		noun 14
		modNum 400
		view 405
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(westDoor doVerb: theVerb &rest)
	)
)
