;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include game.sh)
(use Main)
(use Procs)
(use Feature)
(use LoadMany)
(use Game)
(use Actor)
(use System)

(public
	rmCode 0
)

(local
	local0
	local1
	local2
	local3
	[local4 11] = [65 88 73 90 83 85 66 89 82 86 78]
)
(instance rmCode of Room
	(properties
		lookStr {The sign says that this is the "Doce Omor".__Now what could that mean?}
		picture 380
		style PLAIN
		south 360
	)
	
	(method (init)
		(LoadMany VIEW 381 382 383 384 385 386 387)
		(super init:)
		(cabHandle init:)
		(cabinetDoor init:)
		(portrait init:)
		(book init:)
		(eyeChart init:)
		(doceOmor init:)
		(topLine init:)
		(if (not (Btst 6))
			((ScriptID 385 0) init:)
		else
			((ScriptID 385 1) init:)
			((ScriptID 385 2) init:)
		)
		(self setScript: demo)
	)
	
	(method (dispose)
		((ScriptID 385 0) dispose:)
		(DisposeScript 385)
		(super dispose:)
	)
)

(instance demo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0 (= ticks 100))
			(1
				1
				(= local0 (DoDisplay {'DOCE OMOR'?} 67 35 160 70 320))
				(= ticks 100)
			)
			(2
				2
				(DoDisplay local0)
				(= local1
					(DoDisplay
						{It must be some kind of CODE...}
						67
						35
						160
						70
						320
					)
				)
				(= ticks 100)
			)
			(3
				3
				(DoDisplay local1)
				(= ticks 1)
				((ScriptID 385 0) init: show:)
			)
			(4 4 (= ticks 100))
			(5
				5
				(if (& ((ScriptID 385 0) state?) $0020)
					((ScriptID 385 0)
						select: ((ScriptID 385 0) at: (- [local4 local3] 65)) 0
					)
					(= ticks 30)
				else
					(self changeState: 7)
				)
			)
			(6
				6
				(if (>= (++ local3) 11)
					(self cue:)
				else
					(self changeState: 5)
				)
			)
			(7 7 (= ticks 1))
			(8
				8
				(DoDisplay local2)
				(curRoom newRoom: 2 8)
			)
		)
	)
)

(instance portrait of Prop
	(properties
		x 17
		y 60
		description {portrait of Dr. Brain}
		lookStr {This must be a portrait of Dr. Brain.__He looks friendly enough, but you wonder if he could be hiding something.}
		view 385
		priority 12
		signal $0011
		cycleSpeed 48
	)
)

(instance cabinetDoor of Prop
	(properties
		x 125
		y 161
		description {cabinet}
		lookStr {The cabinet features an impressive digital lock.}
		view 385
		loop 3
		priority 10
		signal $0011
		cycleSpeed 24
	)
)

(instance cabHandle of Prop
	(properties
		x 112
		y 132
		description {cabinet door handle}
		lookStr {Turning this handle will open the cabinet, once the proper combination has been entered on the keypad.}
		view 385
		loop 2
		priority 11
		signal $0011
	)
)

(instance book of View
	(properties
		x 106
		y 109
		description {ornate book}
		view 385
		loop 7
		cel 1
		priority 11
		signal $0011
	)
)

(instance eyeChart of Feature
	(properties
		x 109
		y 133
		z 91
		nsTop 44
		nsLeft 94
		nsBottom 93
		nsRight 128
		description {eye chart}
		onMeCheck $1000
		lookStr {It looks like a doctor's eye chart.__There's something familiar about the first few letters on the top.}
	)
)

(instance doceOmor of Feature
	(properties
		x 129
		y 189
		z 119
		nsLeft 81
		nsBottom 38
		nsRight 161
		description {doce omor}
		onMeCheck $0200
		lookStr {Chiselled into the marble pillar are the words "doce omor".__Dr. Brain's mind must really be scrambled.}
	)
)

(instance topLine of Feature
	(properties
		x 160
		y 189
		z 182
		nsBottom 14
		nsRight 319
		description {cross-beam}
		lookStr {In theory, any one of those decorations could be a secret compartment.__But, in practice, they are just what they seem -- decorations.}
	)
)
