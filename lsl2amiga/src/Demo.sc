;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEMO)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)

;;;(procedure
;;;	FakeInput
;;;	FakeDir			
;;;	FakeKey			
;;;	FakeMouse	
;;;)
(public
	demoRegion		0
	FakeInput		1
	FakeDir			2
	FakeKey			3
	FakeMouse		4
)

(instance demoRegion of Region

	(properties name "DR")

	(method (init)
		(super init:)
		(SetCursor theCursor TRUE SCRNWIDE SCRNHIGH)	;move off screen
		(self setScript: (ScriptID (+ curRoomNum demoScripts)))
		(Dialog time: demoDialogTime)
	);init
	
	(method (dispose)
		(Dialog time: 0)										;restore default
		(script dispose:)										;we ALWAYS have a script
		(DisposeScript (+ curRoomNum demoScripts))	;clean up
		(super dispose:)
	);dispose
	
);demoRegion

(procedure (FakeInput theTime	;&rest arg MUST be a string (far or near)
		&tmp event
	)
	(if theTime (Print &rest #title {Player types:} #time theTime))
	(Format (User inputLineAddr?)  &rest)	;like StrCpy, for far strings too
	((= event (User lastEvent?)) type: saidEvent)
	(Parse (User inputLineAddr?) event)
	(User said: event)
	(event dispose:)
)

(procedure (FakeKey theKey &tmp event)
	(User handleEvent:
		((User lastEvent?)
			type: keyDown
			message: theKey
			yourself:
		)
	)
)

(procedure (FakeDir theDir &tmp event)
	;;theDir is one of: dirStop, dirN, dirNE, ... , dirNW
	(User handleEvent:
		((User lastEvent?)
			type: direction
			message: theDir
			yourself:
		)
	)
)

(procedure (FakeMouse theType theX theY theModifiers &tmp event)
	 
	((= event (User lastEvent?))
		type: theType							;mouseDown or mouseUp
		x: theX
		y: theY
		modifiers: (if (>= argc 3) theModifiers)
	)
	(LocalToGlobal event)
	(SetCursor theCursor TRUE (event x?) (event y?))
	(User handleEvent: event)
)
